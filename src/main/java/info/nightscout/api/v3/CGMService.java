package info.nightscout.api.v3;

import info.nightscout.api.v3.documents.Entry;
import info.nightscout.api.v3.documents.Treatment;
import info.nightscout.api.v3.err.AuthorizationException;
import info.nightscout.api.v3.err.NightscoutException;
import info.nightscout.api.v3.rest.Entries;
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

/**
 *
 */
public class CGMService extends SearchService {

    /**
     * Creates new service.
     *
     * @param baseUrl Base URL, e. g. https://tanjascgms.herokuapp.com/
     * @param token   Access token
     */
    public CGMService(String baseUrl, String token) {
        super(baseUrl, token);
    }

    public void searchEntries(SearchResultListener listener, SearchOptions options) throws NightscoutException {
        Call<List<Entry>> call = super.getRetrofit().create(Entries.class).getEntries(options != null ? options.get() : null);
        call.enqueue(getCallback(listener));
    }

    public List<Entry> searchEntries(SearchOptions options) throws NightscoutException, AuthorizationException {
        Call<List<Entry>> call = super.getRetrofit().create(Entries.class).getEntries(options != null ? options.get() : null);
        try {
            Response<List<Entry>> response = call.execute();
            return getEntriesFromResponse(response);

        } catch (IOException e) {
            throw new NightscoutException(e);
        }
    }

    public void syncEntries(final SearchResultListener listener, Long lastModified, Integer limit, String fields) throws NightscoutException {

        Call<List<Entry>> call = super.getRetrofit().create(Entries.class).syncEntries(lastModified, limit, fields);
        call.enqueue(getCallback(listener));

    }

    public String addEntry(Entry entry) throws NightscoutException {
        Entries service = super.getRetrofit().create(Entries.class);
        Call<Void> call = service.putEntry(entry);
        Response<Void> response;
        try {
            response = call.execute();
            return returnLocation(response);
        } catch (Exception e) {
            throw new NightscoutException(e);
        }
    }

    private Callback<List<Entry>> getCallback(SearchResultListener listener) {
        return new Callback<List<Entry>>() {
            @Override
            public void onResponse(Call<List<Entry>> call, Response<List<Entry>> response) {
                try {
                    listener.onEntry(getEntriesFromResponse(response));
                } catch (AuthorizationException | NightscoutException e) {
                    this.onFailure(call, e);
                }
            }

            @Override
            public void onFailure(Call<List<Entry>> call, Throwable t) {
                listener.onFailure(t);
            }
        };
    }

    private List<Entry> getEntriesFromResponse(Response<List<Entry>> response) throws AuthorizationException, NightscoutException {
        if (response.isSuccessful()) {
            return response.body();
        } else if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
            throw new AuthorizationException(response.message());
        } else {
            throw new NightscoutException(response);
        }
    }
}
