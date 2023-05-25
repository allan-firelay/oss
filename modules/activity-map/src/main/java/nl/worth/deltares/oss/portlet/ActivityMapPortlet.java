package nl.worth.deltares.oss.portlet;


import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import nl.worth.deltares.oss.portlet.constants.ActivityMapPortletKeys;
import nl.worth.deltares.oss.subversion.service.RepositoryLogLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.io.IOException;
import java.util.Collections;


/**
 * @author Pier-Angelo Gaetani @ Worth Systems
 */
@Component(
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=OSS",
                "com.liferay.portlet.header-portlet-css=/css/main.css",
                "com.liferay.portlet.instanceable=false",
                "com.liferay.portlet.footer-portlet-javascript=/js/main.js",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/view.jsp",
                "javax.portlet.name=" + ActivityMapPortletKeys.ACTIVITY_MAP,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
public class ActivityMapPortlet extends MVCPortlet {

    private static final int DEFAULT_LOGS = 10;
    private static final Log LOG = LogFactoryUtil.getLog(ActivityMapPortlet.class);

    public void render(RenderRequest request, RenderResponse response) throws IOException, PortletException {

        //TODO: make activity amount configurable from portlet settings
        String logsJson = getLogsJson(DEFAULT_LOGS).toJSONString();
        request.setAttribute("logsJson", logsJson);

        super.render(request, response);
    }

    private JSONObject getLogsJson(int number) {

        JSONObject logsJson = JSONFactoryUtil.createJSONObject();

        try {
            JSONArray logsArray = RepositoryLogLocalServiceUtil.getLastActivityLogs(number);
            logsJson.put("total", logsArray.length());
            logsJson.put("objects", logsArray);
        } catch (Exception e) {
            LOG.error("Error retrieving RepositoryLogs", e);
            logsJson.put("total", 0);
            logsJson.put("objects", Collections.emptyList());
        }

        return logsJson;
    }

//  private RepositoryLogLocalService repositoryLogLocalService;
//
//  @Reference(unbind = "-")
//  private void setRepositoryLogLocalService(RepositoryLogLocalService repositoryLogLocalService) {
//
//    this.repositoryLogLocalService = repositoryLogLocalService;
//  }
}
