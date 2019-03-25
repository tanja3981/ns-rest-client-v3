package info.nightscout.api.v3;


import info.nightscout.api.v3.documents.Profile;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface Search {

    @GET("/{collection}")
    Call<List<Profile>> search(@Path("collection") String collection );
}
