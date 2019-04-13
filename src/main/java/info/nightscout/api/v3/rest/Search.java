package info.nightscout.api.v3.rest;


import info.nightscout.api.v3.documents.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;


public interface Search {


    @GET("/api/v3/profile/")
    Call<List<Profile>> searchProfiles(@QueryMap Map<String, String> options);

    @GET("/api/v3/devicestatus/")
    Call<List<DeviceStatus>> searchDeviceStatus(@QueryMap Map<String, String> options);

    @GET("/api/v3/entries/")
    Call<List<Entry>> searchEntries(@QueryMap Map<String, String> options);

    @GET("/api/v3/food/")
    Call<List<Food>> searchFood(@QueryMap Map<String, String> options);

    @GET("/api/v3/settings/")
    Call<List<Setting>> searchSettings(@QueryMap Map<String, String> options);

    @GET("/api/v3/treatments/")
    Call<List<Treatment>> searchTreatments(@QueryMap Map<String, String> options);
}
