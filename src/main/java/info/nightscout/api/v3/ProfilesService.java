package info.nightscout.api.v3;

import info.nightscout.api.v3.documents.Profile;
import info.nightscout.api.v3.err.AuthorizationException;
import info.nightscout.api.v3.err.NightscoutException;
import info.nightscout.api.v3.rest.Profiles;
import info.nightscout.api.v3.search.SearchOptions;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Collections;
import java.util.List;


public class ProfilesService extends NightscoutService {

    public ProfilesService(String baseUrl, String token) {
        super(baseUrl, token);
    }

    /**
     * Search in profiles collection without any filter.
     *
     * @return A list of profiles.
     * @throws {@link AuthorizationException}, {@link NightscoutException}
     */
    public List<Profile> searchProfiles() throws AuthorizationException, NightscoutException {
        return this.searchProfiles(null);
    }

    /**
     * Search in profiles collection.
     *
     * @param options
     * @return a list of profiles.
     * @throws {@link AuthorizationException}, {@link NightscoutException}
     */
    public List<Profile> searchProfiles(SearchOptions options) throws AuthorizationException, NightscoutException {

        Call<List<Profile>> call = super.getRetrofit().create(Profiles.class).searchProfiles(options == null ? Collections.emptyMap() : options.get());
        try {
            Response<List<Profile>> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                throw new AuthorizationException(response.message());
            } else {
                throw new NightscoutException(response);
            }
        } catch (IOException e) {
            throw new NightscoutException(e);
        }

    }

    public String addProfile(Profile profile) throws AuthorizationException, NightscoutException {
        Profiles service = super.getRetrofit().create(Profiles.class);
        Call<Void> call = service.putProfile(profile);
        Response<Void> response;
        try {
            response = call.execute();
            return returnLocation(response);
        } catch (Exception e) {
            throw new NightscoutException(e);
        }
    }


}
