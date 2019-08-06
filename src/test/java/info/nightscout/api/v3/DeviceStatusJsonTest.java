package info.nightscout.api.v3;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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

public class DeviceStatusJsonTest {

    private DevicestatusJsonService service;
    private String baseUrl;

    @Before
    public void setup() throws IOException {
        Properties properties = new Properties();
        properties.load(ClassLoader.getSystemResourceAsStream("test.properties"));
        baseUrl = properties.getProperty("baseUrl");
        String token = properties.getProperty("token");
        assertNotNull(baseUrl);
        assertNotNull(token);

        service = new DevicestatusJsonService(baseUrl, token);
        service.disableSSL = true; //disable https requirements for tests
    }

    @Test
    public void addDevicestatus() throws Exception {

        String t1 = createDevicestatus();
        String result = service.addDevicestatus(t1);
        assertNotNull(result);

    }

    private String createDevicestatus() {
        JsonParser parser = new JsonParser();
        JsonObject e = (JsonObject) parser.parse("{\n" +
                "    \"device\": \"openaps://Google Pixel 2 XL\",\n" +
                "    \"pump\": {\n" +
                "        \"battery\": {\n" +
                "            \"percent\": 75\n" +
                "        },\n" +
                "        \"status\": {\n" +
                "            \"status\": \"normal\",\n" +
                "            \"timestamp\": \"2019-08-06T01:35:02Z\"\n" +
                "        },\n" +
                "        \"extended\": {\n" +
                "            \"Version\": \"2.3-dcc8496b5-2019.04.25-22:43\",\n" +
                "            \"PumpIOB\": 1.35,\n" +
                "            \"LastBolus\": \"06.08.19 02:55:37 Mitteleuropäische Sommerzeit\",\n" +
                "            \"LastBolusAmount\": 0.1,\n" +
                "            \"TempBasalAbsoluteRate\": 0,\n" +
                "            \"TempBasalStart\": \"06.08.19 03:34\",\n" +
                "            \"TempBasalRemaining\": 25,\n" +
                "            \"BaseBasalRate\": 0.6,\n" +
                "            \"ActiveProfile\": \"2019/05/08 Fiasp 14.25\"\n" +
                "        },\n" +
                "        \"reservoir\": 237,\n" +
                "        \"clock\": \"2019-08-06T01:40:00Z\"\n" +
                "    },\n" +
                "    \"openaps\": {\n" +
                "        \"suggested\": {\n" +
                "            \"temp\": \"absolute\",\n" +
                "            \"bg\": 114,\n" +
                "            \"tick\": -5,\n" +
                "            \"eventualBG\": 73,\n" +
                "            \"insulinReq\": 0,\n" +
                "            \"deliverAt\": \"2019-08-06T01:40:00.676Z\",\n" +
                "            \"sensitivityRatio\": 1,\n" +
                "            \"predBGs\": {\n" +
                "                \"IOB\": [\n" +
                "                    114,\n" +
                "                    109,\n" +
                "                    105,\n" +
                "                    100,\n" +
                "                    96,\n" +
                "                    93,\n" +
                "                    90,\n" +
                "                    87,\n" +
                "                    84,\n" +
                "                    81,\n" +
                "                    79,\n" +
                "                    77,\n" +
                "                    76,\n" +
                "                    74,\n" +
                "                    73,\n" +
                "                    71,\n" +
                "                    70,\n" +
                "                    69,\n" +
                "                    68,\n" +
                "                    67,\n" +
                "                    67,\n" +
                "                    66,\n" +
                "                    65,\n" +
                "                    65,\n" +
                "                    64,\n" +
                "                    64,\n" +
                "                    63,\n" +
                "                    63,\n" +
                "                    63,\n" +
                "                    62,\n" +
                "                    62,\n" +
                "                    62,\n" +
                "                    62,\n" +
                "                    62,\n" +
                "                    61\n" +
                "                ],\n" +
                "                \"ZT\": [\n" +
                "                    114,\n" +
                "                    110,\n" +
                "                    107,\n" +
                "                    104,\n" +
                "                    101,\n" +
                "                    98,\n" +
                "                    96,\n" +
                "                    94,\n" +
                "                    92,\n" +
                "                    91,\n" +
                "                    89,\n" +
                "                    88,\n" +
                "                    88,\n" +
                "                    87,\n" +
                "                    87,\n" +
                "                    87,\n" +
                "                    87,\n" +
                "                    88,\n" +
                "                    88,\n" +
                "                    89\n" +
                "                ],\n" +
                "                \"COB\": [\n" +
                "                    114,\n" +
                "                    109,\n" +
                "                    105,\n" +
                "                    100,\n" +
                "                    97,\n" +
                "                    93,\n" +
                "                    90,\n" +
                "                    87,\n" +
                "                    84,\n" +
                "                    82,\n" +
                "                    80,\n" +
                "                    78,\n" +
                "                    76,\n" +
                "                    75,\n" +
                "                    74,\n" +
                "                    73,\n" +
                "                    72,\n" +
                "                    71,\n" +
                "                    70,\n" +
                "                    69,\n" +
                "                    69,\n" +
                "                    68,\n" +
                "                    68,\n" +
                "                    67,\n" +
                "                    67,\n" +
                "                    67,\n" +
                "                    66,\n" +
                "                    66,\n" +
                "                    66,\n" +
                "                    66,\n" +
                "                    66,\n" +
                "                    65\n" +
                "                ],\n" +
                "                \"UAM\": [\n" +
                "                    114,\n" +
                "                    109,\n" +
                "                    105,\n" +
                "                    100,\n" +
                "                    96,\n" +
                "                    93,\n" +
                "                    90,\n" +
                "                    87,\n" +
                "                    84,\n" +
                "                    81,\n" +
                "                    79,\n" +
                "                    77,\n" +
                "                    76,\n" +
                "                    74,\n" +
                "                    73,\n" +
                "                    71,\n" +
                "                    70,\n" +
                "                    69,\n" +
                "                    68,\n" +
                "                    67,\n" +
                "                    67,\n" +
                "                    66,\n" +
                "                    65,\n" +
                "                    65,\n" +
                "                    64,\n" +
                "                    64,\n" +
                "                    63,\n" +
                "                    63,\n" +
                "                    63,\n" +
                "                    62,\n" +
                "                    62,\n" +
                "                    62,\n" +
                "                    62,\n" +
                "                    62,\n" +
                "                    61\n" +
                "                ]\n" +
                "            },\n" +
                "            \"COB\": 1,\n" +
                "            \"IOB\": 0.717,\n" +
                "            \"reason\": \"COB: 1, Dev: 2, BGI: -4, ISF: 60, CR: 15, Target: 90, minPredBG 65, minGuardBG 61, IOBpredBG 61, COBpredBG 65, UAMpredBG 61; Eventual BG 73 < 80, setting 30m zero temp.  25m left and 0 ~ req 0U/hr: no temp required\",\n" +
                "            \"timestamp\": \"2019-08-06T01:40:00Z\"\n" +
                "        },\n" +
                "        \"iob\": {\n" +
                "            \"iob\": 0.717,\n" +
                "            \"basaliob\": -0.194,\n" +
                "            \"activity\": 0.012,\n" +
                "            \"time\": \"2019-08-06T01:40:00Z\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"uploaderBattery\": 100,\n" +
                "    \"created_at\": \"2019-08-06T01:40:00Z\",\n" +
                "    \"NSCLIENT_ID\": 1565055600705\n" +
                "}");
e.addProperty("identifier", "test");
e.addProperty("app", "test");
        return e.toString();
    }

    @Test
    public void searchDevicestatusWithoutFilter() throws Exception {

        String resultList = service.searchDevicestatus(null);

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
        DevicestatusService service = new DevicestatusService("https://invalid.url.com/api/v3/", null);
        service.getVersion();

        fail("Exception is missing");
    }

    @Test
    public void searchDevicestatusSortedAndLimited() throws Exception {

        SearchOptions options = SearchOptions.create();
        options.returnCustomFields("srvCreated");
        options.sortDesc("srvCreated");
        options.limit(10);

        String resultList = service.searchDevicestatus(options);
        assertNotNull("No result received.", resultList);
        assertFalse("Empty result received.", resultList.isEmpty());
//        //assertEquals(10, resultList.size());
//        assertNotNull("Missing 'srvCreated'.", resultList.get(0).srvCreated);
//        assertTrue(resultList.get(0).srvCreated - resultList.get(1).srvCreated > 0);
//        assertNull(resultList.get(0).app);
    }

    @Test
    public void searchDevicestatusWithFilter() throws Exception {
        SearchOptions options = SearchOptions.create();
        //options.filter();//TODO
        options.limit(10);
        String resultList = service.searchDevicestatus(options);
        assertNotNull(resultList);
        assertFalse(resultList.isEmpty());

    }
}