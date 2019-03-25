package info.nightscout.api.v3;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetVersion {
    @GET("/api/v3/version")
    Call<Version> getVersion() ;
}
