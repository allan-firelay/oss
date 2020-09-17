package nl.deltares.search.facet.program;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.search.web.portlet.shared.search.PortletSharedSearchContributor;
import com.liferay.portal.search.web.portlet.shared.search.PortletSharedSearchSettings;
import nl.deltares.portal.configuration.DSDSiteConfiguration;
import nl.deltares.portal.model.impl.Event;
import nl.deltares.portal.utils.DsdParserUtils;
import nl.deltares.portal.utils.DsdSessionUtils;
import nl.deltares.search.constans.FacetPortletKeys;
import nl.deltares.search.facet.program.builder.UserProgramFacetBuilder;
import nl.deltares.search.facet.program.builder.UserProgramFacetFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.List;
import java.util.stream.Collectors;

@Component(
        immediate = true,
        property = "javax.portlet.name=" + FacetPortletKeys.USER_PROGRAM_FACET_PORTLET,
        service = PortletSharedSearchContributor.class
)
public class UserProgramFacetPortletSharedContributor implements PortletSharedSearchContributor {
    @Override
    public void contribute(PortletSharedSearchSettings portletSharedSearchSettings) {
        ThemeDisplay themeDisplay = portletSharedSearchSettings.getThemeDisplay();
        User user = themeDisplay.getUser();
        long groupId = themeDisplay.getScopeGroupId();

        try {
            DSDSiteConfiguration configuration = _configurationProvider.
                    getGroupConfiguration(DSDSiteConfiguration.class, groupId);

            String eventId = String.valueOf(configuration.eventId());

            Event event = dsdParserUtils.getEvent(groupId, eventId);

            List<Long> ids = dsdSessionUtils.getUserRegistrations(user, event)
                    .stream()
                    .filter(map -> map.containsKey("resourcePrimaryKey"))
                    .map(map -> map.get("resourcePrimaryKey"))
                    .map(value -> (Long) value)
                    .collect(Collectors.toList());

            //convert resourcePrimaryKeys to articleIds
            List<String> articleIds = ids.stream()
                    .map(item -> event.getRegistration(item).getArticleId())
                    .collect(Collectors.toList());

            portletSharedSearchSettings.addFacet(buildFacet(articleIds, portletSharedSearchSettings));
        } catch (Exception e) {
            LOG.debug("Could not get registrations for user [" + user.getUserId() + "]", e);
        }
    }

    private Facet buildFacet(List<String> ids, PortletSharedSearchSettings portletSharedSearchSettings) {
        _userProgramFacetFactory.setField("articleId_String_sortable");

        UserProgramFacetBuilder userProgramFacetBuilder = new UserProgramFacetBuilder(_userProgramFacetFactory);
        userProgramFacetBuilder.setSearchContext(portletSharedSearchSettings.getSearchContext());
        userProgramFacetBuilder.setArticlesIds(ids);

        return userProgramFacetBuilder.build();
    }

    @Reference
    private DsdSessionUtils dsdSessionUtils;

    @Reference
    private DsdParserUtils dsdParserUtils;

    @Reference
    private UserProgramFacetFactory _userProgramFacetFactory;

    private ConfigurationProvider _configurationProvider;

    @Reference
    protected void setConfigurationProvider(ConfigurationProvider configurationProvider) {
        _configurationProvider = configurationProvider;
    }

    private static final Log LOG = LogFactoryUtil.getLog(UserProgramFacetPortletSharedContributor.class);
}