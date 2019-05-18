package info.nightscout.api.v3.rest;

import info.nightscout.api.v3.documents.Devicestatus;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;


public interface DeviceStatus {

    @GET("/api/v3/devicestatus/")
    Call<List<Devicestatus>> searchDevicestatus(@QueryMap Map<String, String> options);

    @GET("/api/v3/devicestatus/{identifier}")
    Call<Devicestatus> getDevicestatus(@Path("identifier") String identifier, @Query("fields") String fields);

    @POST("/api/v3/devicestatus")
    Call<Void> putDevicestatus(@Body Devicestatus devicestatus);

    @POST("/api/v3/devicestatus/{identifier}")
    Call<Void> replaceDevicestatus(@Path("identifier") String identifier, Devicestatus devicestatus);

    @PATCH("/api/v3/devicestatus/{identifier}")
    Call<Void> patchDevicestatus(@Path("identifier") String identifier, Devicestatus devicestatus);

    @DELETE("/api/v3/devicestatus/{identifier}")
    Call<Void> deleteDevicestatus(@Path("identifier") String identifier, @Query("permanent") Boolean permanent);

    @GET("/api/v3/devicestatus/history")
    Call<List<Devicestatus>> getHistory(@Header("Last-Modified") String lastModified, @Query("limit") Integer limit, @Query("fields") String fields);

    @GET("/api/v3/devicestatus/history/{lastModified}")
    Call<List<Devicestatus>> getHistory(@Path("lastModified") Integer lastModified, @Query("limit") Integer limit, @Query("fields") String fields);


}