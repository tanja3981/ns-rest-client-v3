package info.nightscout.api.v3.rest;

import info.nightscout.api.v3.documents.Treatment;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;


public interface Treatments {

    @GET("/api/v3/treatments/")
    Call<List<Treatment>> searchTreatments(@QueryMap Map<String, String> options);

    @GET("/api/v3/treatments/{identifier}")
    Call<Treatment> getTreatment(@Path("identifier") String identifier, @Query("fields") String fields);

    @POST("/api/v3/treatments")
    Call<Void> putTreatment(@Body Treatment treatment);

    @POST("/api/v3/treatments/{identifier}")
    Call<Void> replaceTreatment(@Path("identifier") String identifier, Treatment treatment);

    @PATCH("/api/v3/treatments/{identifier}")
    Call<Void> patchTreatment(@Path("identifier") String identifier, Treatment treatment);

    @DELETE("/api/v3/treatments/{identifier}")
    Call<Void> deleteTreatment(@Path("identifier") String identifier, @Query("permanent") Boolean permanent);

    @GET("/api/v3/treatments/history")
    Call<List<Treatment>> getHistory(@Header("Last-Modified") String lastModified, @Query("limit") Integer limit, @Query("fields") String fields);

    @GET("/api/v3/treatments/history/{lastModified}")
    Call<List<Treatment>> getHistory(@Path("lastModified") Integer lastModified, @Query("limit") Integer limit, @Query("fields") String fields);


}