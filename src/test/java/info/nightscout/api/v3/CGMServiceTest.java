package info.nightscout.api.v3;

import info.nightscout.api.v3.documents.Devicestatus;
import info.nightscout.api.v3.documents.Entry;
import info.nightscout.api.v3.documents.Profile;
import info.nightscout.api.v3.documents.Treatment;
import info.nightscout.api.v3.err.AuthorizationException;
import info.nightscout.api.v3.err.NightscoutException;
import info.nightscout.api.v3.search.SearchOptions;
import info.nightscout.api.v3.search.SearchResultListener;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import static info.nightscout.api.v3.documents.EventType.CORRECTION;
import static info.nightscout.api.v3.documents.EventType.SNACK;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class CGMServiceTest {

    private CGMService service;
    private String baseUrl;

    @Before
    public void setup() throws IOException {
        Properties properties = new Properties();
        properties.load(ClassLoader.getSystemResourceAsStream("test.properties"));
        baseUrl = properties.getProperty("baseUrl");
        String token = properties.getProperty("token");
        assertNotNull(baseUrl);
        assertNotNull(token);

        service = new CGMService(baseUrl, token);
        service.disableSSL = true; //disable https requirements for tests
    }

    @Test
    public void addEntries() throws Exception {

        Entry t1 = createEntry("RANDOM", "MMOL", 5, "RISING");
        String result = service.addEntry(t1);
        assertNotNull(result);

        Entry t2 = createEntry("Finger", "MGDL", 115, "FLAT");
        result = service.addEntry(t2);
        assertNotNull(result);

    }

    private Entry createEntry(String type, String units, Number sgv, String direction) {
        Entry newSgv = new Entry();
        newSgv.date = System.currentTimeMillis();
        newSgv.app = "CGMServiceTest";
        newSgv.type = type;
        newSgv.units = units;
        newSgv.sgv = sgv;
        newSgv.direction = direction;
        newSgv.identifier = UUID.randomUUID().toString();

        return newSgv;
    }

    @Test(expected = AuthorizationException.class)
    public void searchWithoutToken() throws Exception {
        CGMService service = new CGMService(baseUrl, null);
        service.disableSSL = Boolean.TRUE;
        service.searchEntries(SearchOptions.create());

        fail("Exception is missing");

    }

    @Test(expected = AuthorizationException.class)
    public void searchWrongToken() throws Exception {
        CGMService service = new CGMService(baseUrl, "bla");
        service.disableSSL = Boolean.TRUE;
        service.searchEntries(SearchOptions.create());

        fail("Exception is missing");

    }

    @Test(expected = NightscoutException.class)
    public void testInvalidBaseURL() throws Exception {
        CGMService service = new CGMService("https://invalid.url.com/api/v3/", null);
        service.getVersion();

        fail("Exception is missing");
    }

}
