package info.nightscout.api.v3;


import info.nightscout.api.v3.documents.*;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public interface Search {
    public static SimpleDateFormat dateFormatter = new SimpleDateFormat();
    public static final String FILTER = "filter_parameters";
    public static final String SORT = "sort";
    public static final String SORTDESC = "sort_desc";
    public static final String LIMIT = "limit";
    public static final String SKIP = "skip";
    public static final String FIELDS = "fields";

    @GET("/api/v3/profile/")
    Call<List<Profile>> searchProfiles(@Header("Date") String date, @QueryMap Map<String, String> options);

    @GET("/api/v3/devicestatus/")
    Call<List<DeviceStatus>> searchDeviceStatus(@Header("Date") String date, @QueryMap Map<String, String> options);

    @GET("/api/v3/entries/")
    Call<List<Entry>> searchEntries(@Header("Date") String date, @QueryMap Map<String, String> options);

    @GET("/api/v3/food/")
    Call<List<Food>> searchFood(@Header("Date") String date, @QueryMap Map<String, String> options);

    @GET("/api/v3/settings/")
    Call<List<Setting>> searchSettings(@Header("Date") String date, @QueryMap Map<String, String> options);

    @GET("/api/v3/treatments")
    Call<List<Treatment>> searchTreatments(@Header("Date") String date, @QueryMap Map<String, String> options);
}
