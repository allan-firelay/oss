package nl.deltares.portal.configuration;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;
import nl.deltares.portal.constants.DsdConstants;

@ExtendedObjectClassDefinition(
        category = "dsd-general",
        scope = ExtendedObjectClassDefinition.Scope.GROUP
)
@Meta.OCD(
        id = DsdConstants.DSD_SITE_CONFIGURATIONS_PID,
        localization = "content/Language", name = "dsd-site-configuration"
)
public interface DSDSiteConfiguration {

    @Meta.AD(required = false, deflt = "0")
    long eventId();

    @Meta.AD(required = false, deflt = "", description = "Configure the friendly URL of the registration form page")
    String registrationURL();

    @Meta.AD(required = false, deflt = "", description = "Configure the URL where the General Course Conditions of Deltares can be found")
    String conditionsURL();

    @Meta.AD(required = false, deflt = "/privacy-declaration", description = "Configure the URL where the Privacy Policy of Deltares can be found")
    String privacyURL();

    @Meta.AD(required = false, deflt = "/contact", description = "Configure the URL where the Privacy Policy of Deltares can be found")
    String contactURL();
}