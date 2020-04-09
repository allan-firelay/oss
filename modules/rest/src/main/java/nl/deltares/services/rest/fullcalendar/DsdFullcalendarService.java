package nl.deltares.services.rest.fullcalendar;

import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import nl.deltares.services.rest.exception.LiferayRestException;
import nl.deltares.services.rest.fullcalendar.models.Event;
import nl.deltares.services.rest.fullcalendar.models.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static nl.deltares.services.utils.Helper.getRemoteUser;
import static nl.deltares.services.utils.Helper.toResponse;

/**
 * @author rooij_e
 */
@Path("/calendar")
public class DsdFullcalendarService {

    private final JournalArticleLocalService journalArticleLocalService;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

    public DsdFullcalendarService(JournalArticleLocalService journalArticleLocalService) {
        this.journalArticleLocalService = journalArticleLocalService;
    }

    @GET
    @Path("/startTime")
    @Produces("application/json")
    public String startTime(@Context HttpServletRequest request) throws PortalException, LiferayRestException {

        User user = getRemoteUser(request);
        long companyId = user.getCompanyId();
        long groupId = user.getGroupId();

        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
        jsonObject.put("start", System.currentTimeMillis());
        return jsonObject.toJSONString();
    }

    @GET
    @Path("/events")
    @Produces("application/json")
    public Response events() {

        String[] rooms = {"a", "b", "c", "d", "e"};

        long time = System.currentTimeMillis();

        List<Event> events = new ArrayList<>();
        for (int i = 0; i  < rooms.length; i++) {
            Event event = new Event();
            event.setId(String.valueOf(i));
            event.setResourceId(rooms[i]);
            event.setStart(simpleDateFormat.format(time));
            event.setEnd(simpleDateFormat.format(time + 4 * 3600));
            event.setUrl("http://google.com");
            events.add(event);
        }
        return toResponse(events);

    }

    @GET
    @Path("/resources")
    @Produces("application/json")
    public Response resources() {

        String[] rooms = {"a", "b", "c", "d", "e"};
        String[] colors = {"red", "green", "blue", "yellow", "orange"};
        List<Resource> resources = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Resource resource = new Resource();
            resource.setId(rooms[i]);
            resource.setTitle("Room " + rooms[i]);
            resource.setEventColor(colors[i]);
            resources.add(resource);
        }
        return toResponse(resources);
    }


}