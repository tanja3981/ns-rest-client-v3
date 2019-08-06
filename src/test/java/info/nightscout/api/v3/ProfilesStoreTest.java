package info.nightscout.api.v3;

import info.nightscout.api.v3.auth.LogInterceptor;
import info.nightscout.api.v3.documents.Profile;
import info.nightscout.api.v3.err.AuthorizationException;
import info.nightscout.api.v3.err.NightscoutException;
import info.nightscout.api.v3.search.Filter;
import info.nightscout.api.v3.search.SearchOptions;
import okhttp3.OkHttpClient;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import static org.junit.Assert.*;

public class ProfilesStoreTest {
    private ProfilesService service;
    private String baseUrl;

    @Before
    public void setup() throws IOException {
        Properties properties = new Properties();
        properties.load(ClassLoader.getSystemResourceAsStream("test.properties"));
        baseUrl = properties.getProperty("baseUrl");
        String token = properties.getProperty("token");
        assertNotNull(baseUrl);
        assertNotNull(token);

        service = new ProfilesService(baseUrl, token) {
            public void addTestInterceptors(OkHttpClient.Builder httpClient) {
                LogInterceptor interceptor = new LogInterceptor();
                if (!httpClient.interceptors().contains(interceptor)) {
                    httpClient.addInterceptor(interceptor);
                }
            }
        };
        service.disableSSL = true; //disable https requirements for tests
    }

    @Test
    public void addProfiles() throws Exception {

        Profile t1 = createProfile(12, 1, "ProfilesStoreTest: add1", "Corr");
        String result = service.addProfile(t1);
        assertNotNull(result);

        Profile t2 = createProfile(18, 1.5, "ProfilesStoreTest: add2", "Bolus");
        result = service.addProfile(t2);
        assertNotNull(result);

    }

    private Profile createProfile(double carbs, double insulin, String by, String evType) {
        Profile newProfile = new Profile();
        newProfile.date = System.currentTimeMillis();
        newProfile.app = "restApiV3-UnitTests";
        newProfile.user = "Tanja";
        newProfile.identifier = UUID.randomUUID().toString();
        newProfile.created_at = new Date();
        newProfile.startDate = new Date();

        return newProfile;
    }

    @Test
    public void searchProfilesWithoutFilter() throws Exception {

        List<Profile> resultList = service.searchProfiles(null);

        assertNotNull("No result received.", resultList);
        assertFalse("No results found.", resultList.isEmpty());
    }

    @Test(expected = AuthorizationException.class)
    public void searchWithoutToken() throws Exception {
        ProfilesService service = new ProfilesService(baseUrl, null);
        service.disableSSL = Boolean.TRUE;
        service.searchProfiles(null);

        fail("Exception is missing");

    }

    @Test(expected = AuthorizationException.class)
    public void searchWrongToken() throws Exception {
        ProfilesService service = new ProfilesService(baseUrl, "bla");
        service.disableSSL = Boolean.TRUE;
        service.searchProfiles(null);

        fail("Exception is missing");

    }

    @Test(expected = NightscoutException.class)
    public void testInvalidBaseURL() throws Exception {
        ProfilesService service = new ProfilesService("https://invalid.url.com/api/v3/", null);
        service.getVersion();

        fail("Exception is missing");
    }

    @Test
    public void searchProfilesSortedAndLimited() throws Exception {

        SearchOptions options = SearchOptions.create();
        options.returnCustomFields("srvCreated");
        options.sortDesc("srvCreated");
        options.limit(10);

        List<Profile> resultList = service.searchProfiles(options);
        assertNotNull("No result received.", resultList);
        assertFalse("Empty result received.", resultList.isEmpty());
        assertEquals(10, resultList.size());
        assertNotNull("Missing 'srvCreated'.", resultList.get(0).srvCreated);
        assertTrue(resultList.get(0).srvCreated - resultList.get(1).srvCreated > 0);
        assertNull(resultList.get(0).app);
    }

    @Test
    public void searchProfilesWithFilter() throws Exception {
        SearchOptions options = SearchOptions.create();
        options.filter("user", Filter.EQUALS, "Test");
        options.filter("app", Filter.EQUALS, "restApiV3-UnitTests");
        options.limit(10);
        List<Profile> resultList = service.searchProfiles(options);
        assertFalse(resultList.isEmpty());

        options = SearchOptions.create();
        options.filter("user", Filter.EQUALS, "Tanja");
        resultList = service.searchProfiles(options);
        assertTrue(resultList.isEmpty());
    }
}