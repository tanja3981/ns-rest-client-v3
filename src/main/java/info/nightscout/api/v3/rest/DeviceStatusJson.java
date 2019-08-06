package info.nightscout.api.v3.rest;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;


public interface DeviceStatusJson {

    @GET("/api/v3/devicestatus/")
    Call<String> searchDevicestatus(@QueryMap(encoded = true) Map<String, String> options);

    @GET("/api/v3/devicestatus/{identifier}")
    Call<String> getDevicestatus(@Path("identifier") String identifier, @Query("fields") String fields);

    @POST("/api/v3/devicestatus")
    Call<Void> putDevicestatus(@Body RequestBody devicestatus);

//    @POST("/api/v3/devicestatus/{identifier}")
//    Call<Void> replaceDevicestatus(@Path("identifier") String identifier, String devicestatus);
//
//    @PATCH("/api/v3/devicestatus/{identifier}")
//    Call<Void> patchDevicestatus(@Path("identifier") String identifier, String devicestatus);
//
//    @DELETE("/api/v3/devicestatus/{identifier}")
//    Call<Void> deleteDevicestatus(@Path("identifier") String identifier, @Query("permanent") Boolean permanent);
//
//    @GET("/api/v3/devicestatus/history")
//    Call<List<String>> getHistory(@Header("Last-Modified") String lastModified, @Query("limit") Integer limit, @Query("fields") String fields);
//
//    @GET("/api/v3/devicestatus/history/{lastModified}")
//    Call<List<String>> getHistory(@Path("lastModified") Integer lastModified, @Query("limit") Integer limit, @Query("fields") String fields);


}