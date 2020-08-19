package nl.deltares.forms.portlet.action;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import nl.deltares.dsd.model.Reservation;
import nl.deltares.emails.DsdEmail;
import nl.deltares.forms.constants.OssFormPortletKeys;
import nl.deltares.portal.utils.KeycloakUtils;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + OssFormPortletKeys.DSD_REGISTRATIONFORM,
                "mvc.command.name=/submit/register/form"
        },
        service = MVCActionCommand.class
)
public class SubmitRegistrationActionCommand extends BaseMVCActionCommand {

    @Override
    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
        String redirect = ParamUtil.getString(actionRequest, "redirect");
        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
        User user = themeDisplay.getUser();
        updateUserAttributes(actionRequest, user.getEmailAddress());
        sendRegistrationEmail(actionRequest, user, null, themeDisplay);
        SessionMessages.add(actionRequest, "registration-success");

        if (!redirect.isEmpty()) {
            sendRedirect(actionRequest, actionResponse, redirect);
        }
    }

    private void updateUserAttributes(ActionRequest actionRequest, String emailAddress) {
        try {
            keycloakUtils.updateUserAttributes(emailAddress, getKeycloakAttributes(actionRequest));
        } catch (Exception e) {
            SessionErrors.add(actionRequest, "update-attributes-failed", e.getMessage());
            LOG.debug("Could not update keycloak attributes for user [" + emailAddress + "]", e);
        }
    }

    private Map<String, String> getKeycloakAttributes(ActionRequest actionRequest) {
        Map<String, String> attributes = new HashMap<>();

        for (KeycloakUtils.ATTRIBUTES key : KeycloakUtils.ATTRIBUTES.values()) {
            String value = ParamUtil.getString(actionRequest, key.name());
            if (Validator.isNotNull(value)) {
                attributes.put(key.name(), value);
                LOG.info(key.name() + ": " + value);
            }
        }

        return attributes;
    }

    private void sendRegistrationEmail(ActionRequest actionRequest, User user, Reservation reservation, ThemeDisplay themeDisplay) {
        try {
            sendRegistrationEmail(user, reservation, themeDisplay);
        } catch (Exception e) {
            SessionErrors.add(actionRequest, "send-email-failed", e.getMessage());
            LOG.debug("Could not send registration email for user [" + user.getEmailAddress() + "]", e);
        }
    }

    private void sendRegistrationEmail(User user, Reservation reservation, ThemeDisplay themeDisplay) throws Exception {
        DsdEmail email = new DsdEmail(DsdEmail.DSD_SITE.dsdint);
        ResourceBundle resourceBundle = ResourceBundleUtil.getBundle("content.Language", themeDisplay.getLocale(), getClass());
        email.setResourceBundle(resourceBundle);
        email.setSiteUrl(themeDisplay.getCDNBaseURL() + themeDisplay.getURLCurrent());
        email.sendRegisterEmail(user, reservation);
    }

    @Reference
    private KeycloakUtils keycloakUtils;

    private static final Log LOG = LogFactoryUtil.getLog(SubmitRegistrationActionCommand.class);
}