package info.nightscout.api.v3.rest;

import info.nightscout.api.v3.rest.GetVersion;
import info.nightscout.api.v3.documents.Version;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

import static org.junit.Assert.*;

public class GetVersionTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetVersion() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                 .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("http://localhost:1337/")
                .build();

        GetVersion service = retrofit.create(GetVersion.class);
        Call<Version> call = service.getVersion();
        assertNotNull(call);
        Response<Version> response = call.execute();
        assertNotNull(response);
        Version result = response.body();
        assertNotNull(result);
        assertNotNull(result.version);
        assertNotNull(result.apiVersion);
        assertNotNull(result.srvDate);
        assertNotNull(result.srvDateString);
    }
}