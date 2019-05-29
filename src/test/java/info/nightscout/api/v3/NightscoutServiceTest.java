package info.nightscout.api.v3;

import info.nightscout.api.v3.documents.LastModifiedResult;
import info.nightscout.api.v3.documents.Status;
import info.nightscout.api.v3.documents.Version;
import info.nightscout.api.v3.err.NightscoutException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.*;

public class NightscoutServiceTest {

    private NightscoutService service;
    private String baseUrl;

    @Before
    public void setup() throws IOException {
        Properties properties = new Properties();
        properties.load(ClassLoader.getSystemResourceAsStream("test.properties"));
        baseUrl = properties.getProperty("baseUrl");
        String token = properties.getProperty("token");
        assertNotNull(baseUrl);
        assertNotNull(token);

        service = new NightscoutService(baseUrl, token);
        service.disableSSL = true; //disable https requirements for tests
    }

    @Test(expected = NightscoutException.class)
    public void testMissingUrl() throws NightscoutException {
        NightscoutService service = new NightscoutService("", null);
        service.disableSSL = Boolean.TRUE;
        Version version = service.getVersion();
        assertNull(version);
    }

    @Test(expected = NightscoutException.class)
    public void testInvalidUrl() throws NightscoutException {
        NightscoutService service = new NightscoutService("mailto://bla", null);
        service.disableSSL = Boolean.TRUE;
        Version version = service.getVersion();
        assertNull(version);
    }

    @Test
    public void getVersion() throws NightscoutException {
        NightscoutService service = new NightscoutService(baseUrl, null);
        service.disableSSL = Boolean.TRUE;
        Version version = service.getVersion();

        assertNotNull(version);
        assertEquals("0.11.2-rc2-20190323", version.version);
        assertEquals("3.0.0-alpha", version.apiVersion);
    }

    @Test
    public void getLastModified() throws Exception {
        LastModifiedResult result = service.getLastModified();
        assertNotNull(result);
        assertNotNull(result.srvDate);
        assertNotNull(result.srvDateString);
        assertFalse(result.collections.isEmpty());
    }

    @Test
    public void getStatus() throws Exception {
        Status status = service.getStatus();
        assertNotNull(status);
        assertNotNull(status.apiPermissions);
        assertNotNull(status.apiPermissions.treatments);
        assertEquals("crud", status.apiPermissions.treatments);
    }

}