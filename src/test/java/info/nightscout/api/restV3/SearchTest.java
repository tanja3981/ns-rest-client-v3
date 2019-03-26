package info.nightscout.api.restV3;

import info.nightscout.api.v3.Collection;
import info.nightscout.api.v3.Search;
import info.nightscout.api.v3.documents.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;

public class SearchTest {


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSearchProfiles() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("http://localhost:1337/")
                .build();

        Search service = retrofit.create(Search.class);
        String date = Search.dateFormatter.format(new Date());
        Call<List<Profile>> call = service.searchProfiles(date, Collections.emptyMap());
        assertNotNull(call);

        Response<List<Profile>> response = call.execute();
        assertNotNull(response);
        List<Profile> results = response.body();
        assertNotNull(results);
        assertFalse(results.isEmpty());
        Profile profile = results.get(0);
        assertNotNull(profile.getIdentifier());
        assertNotNull(profile.getDefaultProfile());
    }

    @Test
    public void testSearchTreatments() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("http://localhost:1337/")
                .build();

        Search service = retrofit.create(Search.class);
        String date = Search.dateFormatter.format(new Date());
        Call<List<Treatment>> call = service.searchTreatments(date, Collections.emptyMap());
        assertNotNull(call);
        Response<List<Treatment>> response = call.execute();
        List<Treatment> results = response.body();
        assertFalse(results.isEmpty());
    }

    @Test
    public void testSearchSettings() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("http://localhost:1337/")
                .build();

        Search service = retrofit.create(Search.class);
        String date = Search.dateFormatter.format(new Date());
        Call<List<Setting>> call = service.searchSettings(date, Collections.emptyMap());
        assertNotNull(call);
        Response<List<Setting>> response = call.execute();
        List<Setting> results = response.body();
//TODO empty!?
    }

    @Test
    public void testSearchEntries() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("http://localhost:1337/")
                .build();

        Search service = retrofit.create(Search.class);
        String date = Search.dateFormatter.format(new Date());

        Map<String, String> searchOptions = new HashMap<>();
        searchOptions.put("limit", "50");
        searchOptions.put("sort", "dateString");

        Call<List<Entry>> call = service.searchEntries(date, searchOptions);
        assertNotNull(call);
        Response<List<Entry>> response = call.execute();
        List<Entry> results = response.body();
        assertNotNull(results);
        assertEquals(50, results.size());

        for(Entry e : results) {
            System.out.println(e.getDateString());
        }
    }
}
