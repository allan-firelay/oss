package nl.deltares.portal.utils.impl;

import com.liferay.portal.json.JSONFactoryImpl;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.PropsUtil;
import nl.deltares.mock.MockProps;
import nl.deltares.mock.MockUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * Requires a webinar to be configured in future. Not possible to remove users from past webinars.
 */
@Ignore
public class ANewsSpringUtilsTest {

    private ANewSpringUtils newSpringUtils;

    @Before
    public void setup() throws IOException {
        JSONFactoryUtil jsonFactoryUtil = new JSONFactoryUtil();
        jsonFactoryUtil.setJSONFactory(new JSONFactoryImpl());

        MockProps props = new MockProps();
        Properties properties = new Properties();
        try (InputStream is = getClass().getResourceAsStream("/anewspring/portal-ext.properties")) {
            if (is != null) {
                properties.load(is);
            }
        }
        props.setProperties(properties);
        PropsUtil.setProps(props);

        newSpringUtils = new ANewSpringUtils();

    }

    @Test
    public void testRegisterUser() throws Exception {
        if (!newSpringUtils.isActive()) return;

        User user = new MockUser();
        user.setEmailAddress("test@liferay.com");
        user.setFirstName("Test");
        user.setLastName("User");
        user.setScreenName("anewspring");

        Map<String, String> info = newSpringUtils.getRegistration(user, "PlatformIntro");
        if (info.size() == 0) {
            newSpringUtils.registerUser(user, "PlatformIntro", "testRegisterUser", info);
            info = newSpringUtils.getRegistration(user, "PlatformIntro");
        }
        Assert.assertEquals("PlatformIntro", info.get("id"));
    }

    @Test
    public void testUnregisterUser() throws Exception {
        if (!newSpringUtils.isActive()) return;

        User user = new MockUser();
        user.setEmailAddress("test@liferay.com");
        user.setFirstName("Test");
        user.setLastName("User");
        user.setScreenName("anewspring");

        Map<String, String> info = newSpringUtils.getRegistration(user, "PlatformIntro");
        if (info.size() == 0) {
            newSpringUtils.registerUser(user, "PlatformIntro", "testRegisterUser", info);
            info = newSpringUtils.getRegistration(user, "PlatformIntro");
        }
        Assert.assertNotEquals(0, info.size());
        newSpringUtils.unregisterUser(user, "PlatformIntro", info);
        info = newSpringUtils.getRegistration(user, "PlatformIntro");
        Assert.assertEquals(0, info.size());


    }

}
