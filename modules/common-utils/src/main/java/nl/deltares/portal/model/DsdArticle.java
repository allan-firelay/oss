package nl.deltares.portal.model;

import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import org.w3c.dom.Document;

public interface DsdArticle {

    enum DSD_REQUIRED_REGISTRATION_ATTRIBUTES {org_name, org_address, org_city, org_postal, org_country}
    enum DSD_REQUIRED_PAID_REGISTRATION_ATTRIBUTES {pay_reference}
    enum DSD_STRUCTURE_KEYS {Expert, Location, Building, Room, Eventlocation, Registration, Session, Bustransfer, Dinner, Event, Generic}
    enum DSD_SESSION_KEYS {Breakout, Course, Dinner, OnlineCourse, Symposium, UserDays, Webinar, Workshop, Bustransfer}
    String getTitle();
    long getCompanyId();
    long getGroupId();
    long getResourceId();
    String getArticleId();
    String getStructureKey();
    Document getDocument();
    boolean storeInParentSite();
    String getSmallImageURL(ThemeDisplay themeDisplay);
    JournalArticle getJournalArticle();
}
