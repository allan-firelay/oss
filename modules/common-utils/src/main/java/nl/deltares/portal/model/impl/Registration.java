package nl.deltares.portal.model.impl;

import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.exception.PortalException;
import nl.deltares.portal.model.DsdArticle;
import nl.deltares.portal.utils.JsonContentParserUtils;
import nl.deltares.portal.utils.XmlContentParserUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.w3c.dom.Document;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public abstract class Registration extends AbsDsdArticle {

    private long eventId;
    private int capacity;
    private float price;
    private boolean open;
    private String currency = "&#8364"; //euro sign
    private DsdArticle.DSD_REGISTRATION_KEYS type;
    private DsdArticle.DSD_TOPIC_KEYS topic;
    private Registration parentRegistration = null;
    private boolean overlapWithParent;
    private boolean hasParent;
    private Date startTime;
    private Date endTime;

    public Registration(JournalArticle article) throws PortalException {
        super(article);
        init();
    }

    private void init() throws PortalException {
        try {
            Document document = getDocument();
            String eventId = XmlContentParserUtils.getDynamicContentByName(document, "eventId", false);
            this.eventId =  Long.parseLong(eventId);
            String capacity = XmlContentParserUtils.getDynamicContentByName(document, "capacity", false);
            this.capacity =  Integer.parseInt(capacity);
            String price = XmlContentParserUtils.getDynamicContentByName(document, "price", false);
            this.price =  Float.parseFloat(price);
            String currency = XmlContentParserUtils.getDynamicContentByName(document, "currency", true);
            if (currency != null) this.currency = StringEscapeUtils.escapeHtml4(currency);
            String open = XmlContentParserUtils.getDynamicContentByName(document, "open", true);
            this.open = Boolean.parseBoolean(open);
            String type = XmlContentParserUtils.getDynamicContentByName(document, "type", false);
            this.type = DsdArticle.DSD_REGISTRATION_KEYS.valueOf(type);
            String topic = XmlContentParserUtils.getDynamicContentByName(document, "topic", false);
            this.topic = DsdArticle.DSD_TOPIC_KEYS.valueOf(topic);
            String parentJson = XmlContentParserUtils.getDynamicContentByName(document, "parent", true);
            if (parentJson != null) {
                String overlap = XmlContentParserUtils.getDynamicContentByName(document, "overlaps", true);
                overlapWithParent = Boolean.parseBoolean(overlap);
                hasParent = true;
            }
            startTime = XmlContentParserUtils.parseDateTimeFields(document,"start", "starttime", false);
            endTime = XmlContentParserUtils.parseDateTimeFields(document,"end", "endtime", false);
        } catch (Exception e) {
            throw new PortalException(String.format("Error parsing Registration %s: %s!", getTitle(), e.getMessage()), e);
        }
    }

    private Registration parseParentRegistration(String parentJson) throws PortalException {
        if (parentJson == null){
            throw new NullPointerException("parentJson");
        }
        AbsDsdArticle dsdArticle = parseJsonReference(parentJson);
        if (dsdArticle instanceof Registration) return (Registration) dsdArticle;
        throw new PortalException("Unsupported parent registration type " + dsdArticle.getClass().getName());
    }


    AbsDsdArticle parseJsonReference(String jsonReference) throws PortalException {
        JournalArticle journalArticle = JsonContentParserUtils.jsonReferenceToJournalArticle(jsonReference);
        return AbsDsdArticle.getInstance(journalArticle);
    }

    public boolean isOpen() {
        return open;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public Registration getParentRegistration() throws PortalException {
        if (hasParent && parentRegistration == null){
            String parentJson = XmlContentParserUtils.getDynamicContentByName(getDocument(), "parent", true);
            parentRegistration = parseParentRegistration(parentJson);
        }
        return parentRegistration;
    }

    public boolean isOverlapWithParent() {
        return overlapWithParent;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public String getType() {
        return type.name();
    }

    public String getTopic() {
        return topic.name();
    }

    public long getEventId() {
        return eventId;
    }

    public boolean isEventInPast(){
        return System.currentTimeMillis() > startTime.getTime();
    }

    public boolean isMultiDayEvent(){
        long duration = endTime.getTime() - startTime.getTime();
        return duration > TimeUnit.DAYS.toMillis(1);
    }

}