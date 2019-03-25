package info.nightscout.api.restV3;

import info.nightscout.api.v3.Collection;
import info.nightscout.api.v3.GetVersion;
import info.nightscout.api.v3.Search;
import info.nightscout.api.v3.Version;
import info.nightscout.api.v3.documents.Profile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class SearchTest {


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetProfiles() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("http://localhost:1337/")
                .build();

        Search service = retrofit.create(Search.class);
        Call<List<Profile>> call = service.search("profile");
        assertNotNull(call);

//        call.enqueue(new Callback<List<Profile>>() {
//            @Override
//            public void onResponse(Call<List<Profile>> call, Response<List<Profile>> response) {
//                List<Profile> results = response.body();
//                for (Profile p : results) {
//                    System.out.println(p.toString());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Profile>> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });

        Response<List<Profile>> response = call.execute();
        assertNotNull(response);
        List<Profile> results = response.body();
        assertNotNull(results);
        assertFalse(results.isEmpty());
        Profile profile = results.get(0);
        assertNotNull(profile.getIdentifier());
        assertNotNull(profile.get_id());
    }
}
