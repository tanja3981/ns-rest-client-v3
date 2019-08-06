package info.nightscout.api.v3.rest;

import info.nightscout.api.v3.documents.Entry;
import info.nightscout.api.v3.documents.Treatment;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

public interface Entries {

    @GET("/api/v3/entries/")
    Call<List<Entry>> getEntries(@QueryMap(encoded = true) Map<String, String> options);

    @POST("/api/v3/entries")
    Call<Void> putEntry(@Body Entry newEntry);

    @GET("/api/v3/entries/history/{lastModified}")
    Call<List<Entry>> syncEntries(@Path("lastModified") Long lastModified, @Query("limit") Integer limit, @Query("fields") String fields);
}
