package info.nightscout.api.v3;

import info.nightscout.api.v3.documents.Treatment;
import info.nightscout.api.v3.err.AuthorizationException;
import info.nightscout.api.v3.err.NightscoutException;
import info.nightscout.api.v3.search.Filter;
import info.nightscout.api.v3.search.SearchOptions;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import static org.junit.Assert.*;

public class TreatmentsStoreTest {

    private TreatmentsService service;
    private String baseUrl;

    @Before
    public void setup() throws IOException {
        Properties properties = new Properties();
        properties.load(ClassLoader.getSystemResourceAsStream("test.properties"));
        baseUrl = properties.getProperty("baseUrl");
        String token = properties.getProperty("token");
        assertNotNull(baseUrl);
        assertNotNull(token);

        service = new TreatmentsService(baseUrl, token);
        service.disableSSL = true; //disable https requirements for tests
    }

    @Test
    public void addTreatments() throws Exception {

        Treatment t1 = createTreatment(12, 1, "TreatmentsStoreTest: add1", "Corr");
        String result = service.addTreatment(t1);
        assertNotNull(result);

        Treatment t2 = createTreatment(18, 1.5, "TreatmentsStoreTest: add2", "Bolus");
        result = service.addTreatment(t2);
        assertNotNull(result);

    }

    private Treatment createTreatment(double carbs, double insulin, String by, String evType) {
        Treatment newTreatment = new Treatment();
        newTreatment.date = System.currentTimeMillis();
        newTreatment.app = "restApiV3-UnitTests";
        newTreatment.identifier = UUID.randomUUID().toString();
        newTreatment.carbs = carbs;
        newTreatment.insulin = insulin;
        newTreatment.enteredBy = by;
        newTreatment.eventType = evType;
        return newTreatment;
    }

    @Test
    public void searchTreatmentsWithoutFilter() throws Exception {


        List<Treatment> resultList = service.searchTreatments(null);

        assertNotNull("No result received.", resultList);
        assertFalse("No results found.", resultList.isEmpty());
    }

    @Test(expected = AuthorizationException.class)
    public void searchWithoutToken() throws Exception {
        TreatmentsService service = new TreatmentsService(baseUrl, null);
        service.disableSSL = Boolean.TRUE;
        service.searchTreatments();

        fail("Exception is missing");

    }

    @Test(expected = AuthorizationException.class)
    public void searchWrongToken() throws Exception {
        TreatmentsService service = new TreatmentsService(baseUrl, "bla");
        service.disableSSL = Boolean.TRUE;
        service.searchTreatments();

        fail("Exception is missing");

    }

    @Test(expected = NightscoutException.class)
    public void testInvalidBaseURL() throws Exception {
        SearchService service = new SearchService("https://invalid.url.com/api/v3/", null);
        service.getVersion();

        fail("Exception is missing");
    }

    @Test
    public void searchTreatmentsSortedAndLimited() throws Exception {

        SearchOptions options = SearchOptions.create();
        options.returnCustomFields("srvCreated");
        options.sortDesc("srvCreated");
        options.limit(10);

        List<Treatment> resultList = service.searchTreatments(options);
        assertNotNull("No result received.", resultList);
        assertFalse("Empty result received.", resultList.isEmpty());
        //assertEquals(10, resultList.size());
        assertNotNull("Missing 'srvCreated'.", resultList.get(0).srvCreated);
        assertTrue(resultList.get(0).srvCreated - resultList.get(1).srvCreated > 0);
        assertNull(resultList.get(0).app);
    }

    @Test
    public void searchTreatmentsWithFilter() throws Exception {

        SearchOptions options = SearchOptions.create();
        options.filter("insulin", Filter.LOWER_THAN, "5");
        options.limit(10);
        List<Treatment> resultList = service.searchTreatments(options);
        assertFalse(resultList.isEmpty());

    }
}