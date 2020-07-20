package nl.deltares.portal.model.impl;

import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.exception.PortalException;
import nl.deltares.portal.utils.JsonContentParserUtils;
import nl.deltares.portal.utils.XmlContentParserUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BusRoute extends AbsDsdArticle{
    private boolean storeInParentSite;
    private List<Location> stops = null;
    private List<String> times = null;

    BusRoute(JournalArticle article) throws PortalException {
        super(article);
        init();
    }

    private void init() throws PortalException {
        try {
            Document document = getDocument();
            String storeInParentSite = XmlContentParserUtils.getDynamicContentByName(document, "storeInParentSite", true);
            this.storeInParentSite = Boolean.parseBoolean(storeInParentSite);

        } catch (Exception e) {
            throw new PortalException(String.format("Error parsing content for article %s: %s!", getTitle(), e.getMessage()), e);
        }
    }

    private void loadStops() throws PortalException{
        this.stops = new ArrayList<>();
        this.times = new ArrayList<>();
        NodeList stopNodes = XmlContentParserUtils.getDynamicElementsByName(getDocument(), "location");
        for (int i = 0; i < stopNodes.getLength(); i++) {
            Node stopNode = stopNodes.item(i);
            String locationJson = XmlContentParserUtils.getDynamicContentForNode(stopNode);
            this.stops.add(JsonContentParserUtils.parseLocationJson(locationJson));
            this.times.add(XmlContentParserUtils.getDynamicContentByName(stopNode, "time", false));
        }
    }
    public List<Location> getStops() throws PortalException {
        if (stops == null) loadStops();
        return Collections.unmodifiableList(stops);

    }

    public List<String> getTimes() throws PortalException {
        if (stops == null) loadStops();
        return Collections.unmodifiableList(times);}

    public Location getLocation(String time) throws PortalException {
        if (stops == null) loadStops();
        int i = times.indexOf(time);
        if (i > -1) return stops.get(i);
        return null;
    }

    public String getTime(Location stop) throws PortalException {
        if (stops == null) loadStops();

        int i = stops.indexOf(stop);
        if (i > -1) return times.get(i);
        return null;
    }

    @Override
    public String getStructureKey() {
        return DSD_STRUCTURE_KEYS.Busroute.name();
    }

    @Override
    public boolean storeInParentSite() {
        return storeInParentSite;
    }
}
