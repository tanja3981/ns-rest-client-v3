package info.nightscout.api.v3;

import info.nightscout.api.v3.documents.Treatment;
import info.nightscout.api.v3.search.SearchOptions;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class SearchServiceTest {

    @Test
    public void searchTreatmentsWithoutFilter() throws IOException {
        SearchService service = new SearchService("https://tanjasnewcgms.herokuapp.com/api/v3/treatments/", "alexa-e17e799fab111d2a");

        List<Treatment> resultList = service.searchTreatments(null);

        assertNotNull(resultList);
        assertEquals(1000, resultList.size());
    }

    @Test
    public void searchTreatmentsWithFilter() throws IOException {
        SearchService service = new SearchService("https://tanjasnewcgms.herokuapp.com//api/v3/", "alexa-e17e799fab111d2a");

        SearchOptions options = SearchOptions.create();
        options.returnCustomFields("srvCreated");
        options.sortDesc("srvCreated");
        options.limit(10);
        List<Treatment> resultList = service.searchTreatments(options);
        assertNotNull(resultList);
        assertEquals(10, resultList.size());
        assertNotNull(resultList.get(0).srvCreated);
        assertNotNull(resultList.get(1).srvCreated);
        assertTrue(resultList.get(0).srvCreated - resultList.get(1).srvCreated > 0);
    }
}