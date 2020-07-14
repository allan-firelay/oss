package nl.deltares.portal.utils;

import java.util.Map;

public interface KeycloakUtils {

    enum ATTRIBUTES {org_address, org_city, org_country, org_name, org_postal, org_preferred_payment, org_vat, pay_reference}

    boolean isActive();

    String getUserMailingPath();

    String getAccountPath();

    String getAvatarPath();

    String getAdminAvatarPath();

    byte[] getUserAvatar(String email) throws Exception;

    int updateUserAttributes(String email, Map<String, String> attributes) throws Exception;

    Map<String, String> getUserAttributes(String email) throws Exception;
}
