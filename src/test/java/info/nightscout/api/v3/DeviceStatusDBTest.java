package info.nightscout.api.v3;

import info.nightscout.api.v3.documents.Devicestatus;
import info.nightscout.api.v3.err.AuthorizationException;
import info.nightscout.api.v3.err.NightscoutException;
import info.nightscout.api.v3.search.SearchOptions;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import static org.junit.Assert.*;

public class DeviceStatusDBTest {

    private DevicestatusService service;
    private String baseUrl;

    @Before
    public void setup() throws IOException {
        Properties properties = new Properties();
        properties.load(ClassLoader.getSystemResourceAsStream("test.properties"));
        baseUrl = properties.getProperty("baseUrl");
        String token = properties.getProperty("token");
        assertNotNull(baseUrl);
        assertNotNull(token);

        service = new DevicestatusService(baseUrl, token);
        service.disableSSL = true; //disable https requirements for tests
    }

    @Test
    public void addDevicestatus() throws Exception {

        Devicestatus t1 = createDevicestatus();
        String result = service.addDevicestatus(t1);
        assertNotNull(result);

        Devicestatus t2 = createDevicestatus();
        result = service.addDevicestatus(t2);
        assertNotNull(result);

    }

    private Devicestatus createDevicestatus() {
        Devicestatus newDevicestatus = new Devicestatus();
        newDevicestatus.date = System.currentTimeMillis();
        newDevicestatus.app = "restApiV3-UnitTests";
        newDevicestatus.identifier = UUID.randomUUID().toString();
        newDevicestatus.device = "unix";

        return newDevicestatus;
    }

    @Test
    public void searchDevicestatusWithoutFilter() throws Exception {

        List<Devicestatus> resultList = service.searchDevicestatus(null);

        assertNotNull("No result received.", resultList);
        //assertFalse("No results found.", resultList.isEmpty());
    }

    @Test(expected = AuthorizationException.class)
    public void searchWithoutToken() throws Exception {
        DevicestatusService service = new DevicestatusService(baseUrl, null);
        service.disableSSL = Boolean.TRUE;
        service.searchDevicestatus();
        fail("Exception is missing");
    }

    @Test(expected = AuthorizationException.class)
    public void searchWrongToken() throws Exception {
        DevicestatusService service = new DevicestatusService(baseUrl, "bla");
        service.disableSSL = Boolean.TRUE;

        service.searchDevicestatus();
        fail("Exception is missing");
    }

    @Test(expected = NightscoutException.class)
    public void testInvalidBaseURL() throws Exception {
        SearchService service = new SearchService("https://invalid.url.com/api/v3/", null);
        service.getVersion();

        fail("Exception is missing");
    }

    @Test
    public void searchDevicestatusSortedAndLimited() throws Exception {

        SearchOptions options = SearchOptions.create();
        options.returnCustomFields("srvCreated");
        options.sortDesc("srvCreated");
        options.limit(10);

        List<Devicestatus> resultList = service.searchDevicestatus(options);
        assertNotNull("No result received.", resultList);
        assertFalse("Empty result received.", resultList.isEmpty());
        //assertEquals(10, resultList.size());
        assertNotNull("Missing 'srvCreated'.", resultList.get(0).srvCreated);
        assertTrue(resultList.get(0).srvCreated - resultList.get(1).srvCreated > 0);
        assertNull(resultList.get(0).app);
    }

    @Test
    public void searchDevicestatusWithFilter() throws Exception {
        SearchOptions options = SearchOptions.create();
        //options.filter();//TODO
        options.limit(10);
        List<Devicestatus> resultList = service.searchDevicestatus(options);
        assertFalse(resultList.isEmpty());

    }
}