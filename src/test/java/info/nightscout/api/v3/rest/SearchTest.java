package info.nightscout.api.v3.rest;

import info.nightscout.api.v3.documents.Entry;
import info.nightscout.api.v3.documents.Profile;
import info.nightscout.api.v3.documents.Setting;
import info.nightscout.api.v3.documents.Treatment;
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
    final String token = "alexa-e17e799fab111d2a";

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
        Call<List<Profile>> call = service.searchProfiles(Collections.emptyMap());
        assertNotNull(call);

        Response<List<Profile>> response = call.execute();
        assertNotNull(response);
        List<Profile> results = response.body();
        assertNotNull(results);
        assertFalse(results.isEmpty());
        Profile profile = results.get(0);
        assertNotNull(profile.identifier);
        assertNotNull(profile.defaultProfile);
    }

    @Test
    public void testSearchTreatments() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("http://localhost:1337/")
                .build();

        Search service = retrofit.create(Search.class);
        Call<List<Treatment>> call = service.searchTreatments(Collections.emptyMap());
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
        Call<List<Setting>> call = service.searchSettings(Collections.emptyMap());
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

        Map<String, String> searchOptions = new HashMap<>();
        searchOptions.put("limit", "50");
        searchOptions.put("sort", "dateString");

        Call<List<Entry>> call = service.searchEntries(searchOptions);
        assertNotNull(call);
        Response<List<Entry>> response = call.execute();
        List<Entry> results = response.body();
        assertNotNull(results);
        assertEquals(50, results.size());

        for (Entry e : results) {
            System.out.println(e.dateString);
        }
    }
}
