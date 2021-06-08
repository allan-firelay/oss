package nl.deltares.tasks.impl;

import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateUtil;
import nl.deltares.dsd.model.BillingInfo;
import nl.deltares.portal.model.impl.AbsDsdArticle;
import nl.deltares.portal.model.impl.Event;
import nl.deltares.portal.model.impl.Registration;
import nl.deltares.portal.model.impl.SessionRegistration;
import nl.deltares.portal.utils.*;
import nl.deltares.tasks.AbstractDataRequest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.*;

import static nl.deltares.tasks.DataRequest.STATUS.*;

public class DownloadEventRegistrationsRequest extends AbstractDataRequest {

    private static final Log logger = LogFactoryUtil.getLog(DownloadEventRegistrationsRequest.class);
    private final String eventId;
    private final DsdParserUtils dsdParserUtils;
    private final DsdSessionUtils dsdSessionUtils;
    private final KeycloakUtils keycloakUtils;
    private final Group group;
    private final Locale locale;
    private final DsdJournalArticleUtils dsdJournalArticleUtils;
    private final boolean deleteOnCompletion;
    private final WebinarUtilsFactory webinarUtilsFactory;

    public DownloadEventRegistrationsRequest(String id, long currentUser, String eventId, Group siteGroup, Locale locale,
                                             DsdParserUtils dsdParserUtils, DsdSessionUtils dsdSessionUtils,
                                             KeycloakUtils keycloakUtils, DsdJournalArticleUtils dsdJournalArticleUtils, WebinarUtilsFactory webinarUtilsFactory, boolean delete) throws IOException {
        super(id, currentUser);
        this.eventId = eventId;
        this.group = siteGroup;
        this.locale = locale;
        this.dsdParserUtils = dsdParserUtils;
        this.dsdSessionUtils = dsdSessionUtils;
        this.keycloakUtils = keycloakUtils;
        this.dsdJournalArticleUtils = dsdJournalArticleUtils;
        this.webinarUtilsFactory = webinarUtilsFactory;
        this.deleteOnCompletion = delete;

    }

    @Override
    public STATUS call() {

        if (getStatus() == available) return status;
        status = running;

        init();
        try {
            File tempFile = new File(getExportDir(), id + ".tmp");
            if (tempFile.exists()) Files.deleteIfExists(tempFile.toPath());

            Event event = getEvent();

            List<Map<String, Object>> registrationRecords = dsdSessionUtils.getRegistrations(event);

            totalCount = registrationRecords.size();

            if (registrationRecords.size() == 0) {
                status = nodata;
                fireStateChanged();
                return status;
            }
            Map<Long, User> userCache = new HashMap<>();
            Map<String, List<String>> webinarKeyCache = new HashMap<>();
            Map<Long, Map<String, String>> userAttributeCache = new HashMap<>();

            //Create local session because the servlet session will close after call to endpoint is completed
            try (PrintWriter writer = new PrintWriter(new FileWriter(tempFile))){

                StringBuilder header = new StringBuilder("event,registration,start date,topic,type,email,firstName,lastName,webinarProvider,registrationStatus,remarks");
                for (KeycloakUtils.BILLING_ATTRIBUTES value : KeycloakUtils.BILLING_ATTRIBUTES.values()) {
                    header.append(',');
                    header.append(value.name());
                }
                writer.println(header);
                registrationRecords.forEach(recordObjects ->{
                        writeRecord(writer, recordObjects, event, userCache, userAttributeCache, webinarKeyCache, locale);
                        if (Thread.interrupted()){
                            throw new RuntimeException("Thread interrupted");
                        }
                    }
                );

                status = available;

            } catch (Exception e) {
                errorMessage = e.getMessage();
                logger.warn("Error serializing csv content: %s", e);
                status = terminated;
            }

            if (status == available) {
                if (dataFile.exists()) Files.deleteIfExists(dataFile.toPath());
                Files.move(tempFile.toPath(), dataFile.toPath());
            }

            if (deleteOnCompletion) {
                dsdSessionUtils.deleteEventRegistrations(event.getGroupId(), event.getResourceId());
            }
        } catch (IOException | PortalException e) {
            errorMessage = e.getMessage();
            status = terminated;
        }
        fireStateChanged();

        return status;
    }

    private Event getEvent() throws PortalException {
        try {
            return dsdParserUtils.getEvent(group.getGroupId(), eventId, locale);
        } catch (PortalException e) {
            List<Group> children = group.getChildren(true);
            for (Group child : children) {
                try {
                    return dsdParserUtils.getEvent(child.getGroupId(), eventId, locale);
                } catch (PortalException portalException) {
                    //
                }
            }
        }
        throw new PortalException("Could not find event for id " + eventId);
    }

    private void writeRecord(PrintWriter writer, Map<String, Object> record, Event event, Map<Long, User> userCache,
                             Map<Long, Map<String, String>> userAttributeCache, Map<String, List<String>> courseRegistrationsCache, Locale locale) {

        Long registrationId = (Long) record.get("resourcePrimaryKey");
        Registration registration = getRegistration(registrationId, event, locale);
        if (registration == null){
            logger.error(String.format("Cannot find registration for registrationId %d", registrationId));
            return;
        }
        Long userId = (Long) record.get("userId");
        User user = userCache.get(userId);
        if (user == null){
            try {
                user = UserLocalServiceUtil.getUser(userId);
            } catch (PortalException e) {
                logger.error(String.format("Cannot find registered DSD user %d: %s", userId, e.getMessage()));
                return;
            }
            userCache.put(userId, user);
        }
        StringBuilder line = new StringBuilder();
        writeField(line, event.getTitle());
        writeField(line, registration.getTitle());
        writeField(line, DateUtil.getDate((Date) record.get("startTime"),"yyyy-MM-dd", locale));
        writeField(line, registration.getTopic());
        writeField(line, registration.getType());
        writeField(line, user.getEmailAddress());
        writeField(line, user.getFirstName());
        writeField(line, user.getLastName());
        writeWebinarInfo(line, user, registration, courseRegistrationsCache);
        try {
            Map<String, String> userPreferences = JsonContentUtils.parseJsonToMap((String) record.get("userPreferences"));
            writeField(line, userPreferences.get("remarks"));
            BillingInfo billingInfo = getBillingInfo(userPreferences);
            writeBillingInfo(line, billingInfo, user, userAttributeCache);
        } catch (JSONException e) {
            logger.error(String.format("Invalid userPreferences '%s': %s", record.get("userPreferences"), e.getMessage()));
            line.append(','); //empty remarks
            Arrays.stream(KeycloakUtils.BILLING_ATTRIBUTES.values()).iterator().forEachRemaining(billing_attributes -> line.append(',')); //empty billing info
        }
        writer.println(line);
        processedCount++;
    }

    private void writeField(StringBuilder line, String value) {
        if (value != null) {
            if (value.contains("\n")) {
                value = value.replaceAll("\n", " ");
            }
            boolean addQuotes = value.contains(",");
            if (addQuotes){
                line.append("\"");
            }
            line.append(value);
            if (addQuotes){
                line.append("\"");
            }
        }
        line.append(',');
    }

    private void writeWebinarInfo(StringBuilder line, User user, Registration registration, Map<String, List<String>> courseRegistrationsCache) {

        if (!webinarUtilsFactory.isWebinarSupported(registration)) {
            line.append(','); //webinarProvider
            line.append(','); //registrationStatus
            return;
        }

        SessionRegistration sessionRegistration = (SessionRegistration) registration;
        writeField(line, sessionRegistration.getWebinarProvider());

        WebinarUtils webinarUtils;
        try {
            webinarUtils = webinarUtilsFactory.newInstance(registration);
        } catch (Exception e) {
            logger.error(String.format("Failed to get utils for webinar provider %s: %s", ((SessionRegistration) registration).getWebinarProvider(), e.getMessage()));
            line.append(','); //webinarProvider
            line.append(','); //registrationStatus
            return;
        }
        String webinarKey = sessionRegistration.getWebinarKey();
        List<String> courseRegistrations = courseRegistrationsCache.get(webinarKey);
        if (courseRegistrations == null){
            try {
                courseRegistrations = webinarUtils.getAllCourseRegistrations(webinarKey);
            } catch (Exception e){
                logger.error(String.format("Failed to get course registrations for webinar %s: %s", webinarKey, e.getMessage()));
                courseRegistrations = Collections.emptyList();
            }
            courseRegistrationsCache.put(webinarKey, courseRegistrations);
        }
        String status = webinarUtils.isUserInCourseRegistrationsList(courseRegistrations, user) ? "registered" : null;
        writeField(line, status);
    }

    private BillingInfo getBillingInfo(Map<String, String> propertyMap) {

        BillingInfo billingInfo = new BillingInfo();
        //Write billing information.
        for (KeycloakUtils.BILLING_ATTRIBUTES key : KeycloakUtils.BILLING_ATTRIBUTES.values()) {
            String value = propertyMap.get(key.name());
            if (value != null) billingInfo.setAttribute(key, value);
        }
        return billingInfo;
    }

    private void writeBillingInfo(StringBuilder line, BillingInfo billingInfo, User user, Map<Long, Map<String, String>> userAttributeCache){

        Map<String, String> userAttributes = userAttributeCache.get(user.getUserId());
        if (billingInfo.isUseOrganization() && userAttributes == null){
            try {
                userAttributes = keycloakUtils.getUserAttributes(user.getEmailAddress());
            } catch (Exception e) {
                logger.error(String.format("Cannot find attributes for DSD user %d: %s", user.getUserId(), e.getMessage()));
                userAttributes = new HashMap<>();
            }
            userAttributeCache.put(user.getUserId(), userAttributes);
        }
        //Write billing information. If no billing info then get values from user attributes
        for (KeycloakUtils.BILLING_ATTRIBUTES key : KeycloakUtils.BILLING_ATTRIBUTES.values()) {
            String billingAttribute = billingInfo.getAttribute(key);
            if (billingAttribute == null) {
                billingAttribute = userAttributes.get(BillingInfo.getCorrespondingUserAttributeKey(key).name());
            }
            writeField(line, billingAttribute);
        }
    }

    private Registration getRegistration(Long registrationId, Event event, Locale locale) {

        if (event != null) {
            Registration registration = event.getRegistration(registrationId, locale);
            if (registration != null) return registration;
        }
        //Something wrong. Registration not loaded in Event check DB.
        try {
            JournalArticle latestArticle = dsdJournalArticleUtils.getLatestArticle(registrationId);
            AbsDsdArticle dsdArticle = dsdParserUtils.toDsdArticle(latestArticle, locale);
            if (!(dsdArticle instanceof Registration)) return null;
            return (Registration) dsdArticle;
        } catch (PortalException e) {
            return null;
        }
    }

}
