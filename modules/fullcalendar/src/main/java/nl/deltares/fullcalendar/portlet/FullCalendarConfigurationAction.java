package nl.deltares.fullcalendar.portlet;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import nl.deltares.fullcalendar.constants.FullcalendarPortletKeys;
import nl.deltares.portal.utils.DsdJournalArticleUtils;
import nl.deltares.portal.utils.DsdParserUtils;
import nl.deltares.portal.utils.JsonContentUtils;
import org.osgi.service.component.annotations.*;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static nl.deltares.fullcalendar.portlet.FullCalendarUtils.getTypeMap;

@Component(
        configurationPid = "nl.deltares.npm.react.portlet.fullcalendar.portlet.FullCalendarConfiguration",
        configurationPolicy = ConfigurationPolicy.OPTIONAL, immediate = true,
        property = {
                "javax.portlet.name=" + FullcalendarPortletKeys.FULLCALENDAR
        },
        service = ConfigurationAction.class
)

public class FullCalendarConfigurationAction extends DefaultConfigurationAction {

    @Override
    public void include(PortletConfig portletConfig, HttpServletRequest httpServletRequest,
                        HttpServletResponse httpServletResponse) throws Exception {

        httpServletRequest.setAttribute(
                FullCalendarConfiguration.class.getName(),
                _configuration);
        httpServletRequest.setAttribute(DsdParserUtils.class.getName(), dsdParserUtils);
        httpServletRequest.setAttribute(ConfigurationProvider.class.getName(), _configurationProvider);

        ThemeDisplay themeDisplay = (ThemeDisplay) httpServletRequest.getAttribute(WebKeys.THEME_DISPLAY);

        try {
            Map<String, String> typeMap = getTypeMap(themeDisplay, dsdJournalArticleUtils, _configurationProvider);
            httpServletRequest.setAttribute("typeMap", typeMap);
        } catch (PortalException e) {
            throw new PortletException("Could not get options for field 'registrationType' in structure SESSIONS: " + e.getMessage(), e);
        }

        super.include(portletConfig, httpServletRequest, httpServletResponse);
    }

    @Override
    public void processAction(PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse)
            throws Exception {

        String baseUrl = ParamUtil.getString(actionRequest, "baseUrl");
        setPreference(actionRequest, "baseUrl", baseUrl);
        Map<String, String> colorMap = convertColorsToMap(actionRequest);
        setPreference(actionRequest, "sessionColorMap", JsonContentUtils.formatMapToJson(colorMap));
        setPreference(actionRequest, "defaultView", ParamUtil.getString(actionRequest, "defaultView"));

        super.processAction(portletConfig, actionRequest, actionResponse);
    }

    private Map<String, String> convertColorsToMap(ActionRequest actionRequest) throws PortletException {

        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
        try {
            Map<String, String> typeMap = getTypeMap(themeDisplay, dsdJournalArticleUtils, _configurationProvider);
            HashMap<String, String> colorMap = new HashMap<>();
            typeMap.keySet().forEach(sessionKey -> {
                String value = ParamUtil.getString(actionRequest, sessionKey);
                if (value.isEmpty()) return;
                colorMap.put(sessionKey, value);
            });
            return colorMap;

        } catch (PortalException e) {
            throw new PortletException("Could not get options for field 'registrationType' in structure SESSIONS: " + e.getMessage(), e);
        }

    }

    /**
     *
     * (1)If a method is annoted with @Activate then the method will be called at the time of activation of the component
     *  so that we can perform initialization task
     *
     * (2) This class is annoted with @Component where we have used configurationPid with id com.proliferay.configuration.DemoConfiguration
     * So if we modify any configuration then this method will be called.
     */
    @Activate
    @Modified
    protected void activate(Map<Object, Object> properties) {
        _configuration = ConfigurableUtil.createConfigurable(
                FullCalendarConfiguration.class, properties);
    }

    @Reference
    private DsdParserUtils dsdParserUtils;

    @Reference
    DsdJournalArticleUtils dsdJournalArticleUtils;

    private volatile FullCalendarConfiguration _configuration;

    private ConfigurationProvider _configurationProvider;

    @Reference
    protected void setConfigurationProvider(ConfigurationProvider configurationProvider) {
        _configurationProvider = configurationProvider;
    }

}
