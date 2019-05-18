package info.nightscout.api.v3.search;

import info.nightscout.api.v3.search.Filter;
import info.nightscout.api.v3.search.SearchOptions;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class SearchOptionsTest {

    @Test
    public void get() {
        SearchOptions result = SearchOptions.create();
        Map<String, String> map = result.get();
        assertNotNull(map);
        assertTrue("Map must be empty", map.isEmpty());
    }

    @Test
    public void filter() {
        SearchOptions result = SearchOptions.create();
        result.filter("app", Filter.EQUALS, "bla");
        Map<String, String> map = result.get();
        assertNotNull(map);
        assertFalse("Filter is not set.", StringUtils.isEmpty(map.get("app_eq")));
        assertEquals(map.get("app_eq"), "bla");

        //append another filter
        result.filter("insulin", Filter.GREATER_THAN, "5");
        map = result.get();
        assertEquals("5", map.get("insulin_gt"));

    }

    @Test
    public void limit() {
        SearchOptions result = SearchOptions.create();
        result.limit(null);
        Map<String, String> map = result.get();
        assertNotNull(map);
        assertTrue("The map may not contain any search params", map.isEmpty());

        result.limit(5);
        map = result.get();
        assertNotNull(map);
        assertEquals(map.get("limit"), "5");
    }

    @Test
    public void skip() {
        SearchOptions options = SearchOptions.create();
        options.skip(null);
        Map<String, String> result = options.get();
        assertNotNull(result);
        assertTrue("The map may not containt search params.", result.isEmpty());

        options.skip(5);
        result = options.get();
        assertNotNull(result);
        assertEquals("5", result.get("skip"));
    }

    @Test
    public void sortBy() {
        SearchOptions options = SearchOptions.create();
        options.sortBy("field1");
        Map<String, String> result = options.get();
        assertNotNull(result);
        assertEquals("field1", result.get("sort"));

        //only one sort option is allowed, ensure the other was deleted
        options.sortBy("field2");
        result = options.get();
        assertNotNull(result);
        assertEquals("field2", result.get("sort"));
    }

    @Test
    public void sortDesc() {
        SearchOptions options = SearchOptions.create();
        options.sortDesc("field1");
        Map<String, String> result = options.get();
        assertNotNull(result);
        assertEquals("field1", result.get("sort_desc"));

        options.sortDesc("field2");
        result = options.get();
        assertNotNull(result);
        assertEquals("field2", result.get("sort_desc"));
    }

    @Test
    public void sortBoth1() {
        SearchOptions options = SearchOptions.create();
        options.sortBy("field1");
        options.sortDesc("field2");
    }

    @Test
    public void sortBoth2() {
        SearchOptions options = SearchOptions.create();
        options.sortDesc("field1");
        options.sortBy("field2");
    }

    @Test
    public void returnCustomFields() {
        SearchOptions options = SearchOptions.create();
        options.returnCustomFields("field1,field2");
        Map<String, String> result = options.get();
        assertNotNull(result);
        assertEquals("field1,field2", result.get("fields"));

        options.returnCustomFields("field3");
        result = options.get();
        assertNotNull(result);
        assertEquals("field1,field2,field3", result.get("fields"));
    }

    @Test
    public void returnClientFields() {
        SearchOptions options = SearchOptions.create();
        options.returnCustomFields("field1,field2");
        Map<String, String> result = options.get();
        assertNotNull(result);
        assertEquals("field1,field2", result.get("fields"));

        options.returnClientFields();
        result = options.get();
        assertNotNull(result);
        assertEquals("_client", result.get("fields"));
    }
}