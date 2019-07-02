package info.nightscout.api.v3;

import info.nightscout.api.v3.documents.Profile;
import info.nightscout.api.v3.documents.Treatment;
import info.nightscout.api.v3.err.AuthorizationException;
import info.nightscout.api.v3.err.NightscoutException;
import info.nightscout.api.v3.rest.Profiles;
import info.nightscout.api.v3.rest.Treatments;
import info.nightscout.api.v3.search.SearchOptions;
import info.nightscout.api.v3.search.SearchResultListener;
import info.nightscout.api.v3.search.SearchService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Collections;
import java.util.List;


public class ProfilesService extends SearchService {

    public ProfilesService(String baseUrl, String token) {
        super(baseUrl, token);
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

    /**
     * Search in profiles collection asynchronously. On result the callback is called to return the results.
     *
     * @param cb      to return results or failure.
     * @param options the search options
     * @throws {@link NightscoutException}
     */
    public void searchProfiles(SearchResultListener cb, SearchOptions options) throws NightscoutException {

        Call<List<Profile>> call = super.getRetrofit().create(Profiles.class).searchProfiles(options == null ? Collections.emptyMap() : options.get());
        call.enqueue(new Callback<List<Profile>>() {
            @Override
            public void onResponse(Call<List<Profile>> call, Response<List<Profile>> response) {

                if (response.isSuccessful()) {
                    cb.onProfile(response.body());
                } else if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                    onFailure(call, new AuthorizationException(response.message()));
                } else {
                    onFailure(call, new NightscoutException(response));
                }
            }

            @Override
            public void onFailure(Call<List<Profile>> call, Throwable t) {
                cb.onFailure(t);
            }
        });
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

    public void syncProfiles(final SearchResultListener listener, Long lastModified, Integer limit, String fields) throws NightscoutException {

        Call<List<Profile>> call = super.getRetrofit().create(Profiles.class).syncProfiles(lastModified, limit, fields);
        call.enqueue(getCallback(listener));

    }

    private List<Profile> getProfilesFromResponse(Response<List<Profile>> response) throws AuthorizationException, NightscoutException {
        if (response.isSuccessful()) {
            return response.body();
        } else if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
            throw new AuthorizationException(response.message());
        } else {
            throw new NightscoutException(response);
        }
    }

    private Callback<List<Profile>> getCallback(SearchResultListener listener) {
        return new Callback<List<Profile>>() {
            @Override
            public void onResponse(Call<List<Profile>> call, Response<List<Profile>> response) {
                try {
                    listener.onProfile(getProfilesFromResponse(response));
                } catch (AuthorizationException | NightscoutException e) {
                    this.onFailure(call, e);
                }
            }

            @Override
            public void onFailure(Call<List<Profile>> call, Throwable t) {
                listener.onFailure(t);
            }
        };
    }
}
