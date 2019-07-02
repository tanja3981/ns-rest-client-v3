package info.nightscout.api.v3.rest;

import info.nightscout.api.v3.documents.Profile;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;


public interface Profiles {

    @GET("/api/v3/profile/")
    Call<List<Profile>> searchProfiles(@QueryMap(encoded = true) Map<String, String> options);

    @GET("/api/v3/profile/{identifier}")
    Call<Profile> getProfile(@Path("identifier") String identifier, @Query("fields") String fields);

    @POST("/api/v3/profile")
    Call<Void> putProfile(@Body Profile profile);

    @POST("/api/v3/profile/{identifier}")
    Call<Void> replaceProfile(@Path("identifier") String identifier, Profile profile);

    @PATCH("/api/v3/profile/{identifier}")
    Call<Void> patchProfile(@Path("identifier") String identifier, Profile profile);

    @DELETE("/api/v3/profile/{identifier}")
    Call<Void> deleteProfile(@Path("identifier") String identifier, @Query("permanent") Boolean permanent);

//    @GET("/api/v3/profile/history")
//    Call<List<Profile>> syncProfiles(@Header("Last-Modified") String lastModified, @Query("limit") Integer limit, @Query("fields") String fields);

    @GET("/api/v3/profile/history/{lastModified}")
    Call<List<Profile>> syncProfiles(@Path("lastModified") Long lastModified, @Query("limit") Integer limit, @Query("fields") String fields);


}