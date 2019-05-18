package info.nightscout.api.v3;

import info.nightscout.api.v3.documents.LastModifiedResult;
import info.nightscout.api.v3.documents.Version;
import info.nightscout.api.v3.err.AuthorizationException;
import info.nightscout.api.v3.err.NightscoutException;
import org.junit.Before;
import org.junit.BeforeClass;
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
        String token = properties.getProperty("token");deviceStatus
        assertNotNull(baseUrl);
        assertNotNull(token);

        service = new NightscoutService(baseUrl, token);
        service.disableSSL = true; //disable https requirements for tests
    }

    @Test
    public void getVersion() throws NightscoutException, AuthorizationException {
        NightscoutService service = new NightscoutService(baseUrl, null);
        service.disableSSL = Boolean.TRUE;
        Version version = service.getVersion();

        assertNotNull(version);
        assertEquals("0.11.2-rc2-20190323", version.getVersion());
        assertEquals("3.0.0-alpha", version.getApiVersion());
    }

    @Test
    public void getLastModified() throws Exception {
        LastModifiedResult result = service.getLastModified();
        assertNotNull(result);
        assertNotNull(result.srvDate);
        assertNotNull(result.srvDateString);
        assertFalse(result.collections.isEmpty());
    }

}