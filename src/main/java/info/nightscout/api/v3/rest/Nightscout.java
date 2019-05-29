package info.nightscout.api.v3.rest;

import info.nightscout.api.v3.documents.LastModifiedResult;
import info.nightscout.api.v3.documents.Status;
import info.nightscout.api.v3.documents.Version;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Nightscout {
    @GET("/api/v3/version")
    Call<Version> getVersion();

    @GET("/api/v3/lastModified")
    Call<LastModifiedResult> getLastModified();

    @GET("/api/v3/status")
    Call<Status> getStatus();
}
