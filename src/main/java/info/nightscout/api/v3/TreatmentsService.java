package info.nightscout.api.v3;

import info.nightscout.api.v3.documents.Treatment;
import info.nightscout.api.v3.err.AuthorizationException;
import info.nightscout.api.v3.err.NightscoutException;
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
public class TreatmentsService extends SearchService {

    /**
     * Creates new service.
     *
     * @param baseUrl Base URL, e. g. https://tanjascgms.herokuapp.com/
     * @param token   Access token
     */
    public TreatmentsService(String baseUrl, String token) {
        super(baseUrl, token);
    }

    /**
     * Search in treatments collection without any filter.
     *
     * @return a list of treatments
     * @throws {@link AuthorizationException}, {@link NightscoutException}
     */
    public List<Treatment> searchTreatments() throws AuthorizationException, NightscoutException {
        return this.searchTreatments(null);
    }

    /**
     * Search in treatments collection.
     *
     * @param options The search options and filters.
     * @return a list of treatments
     * @throws {@link AuthorizationException}, {@link NightscoutException}
     */
    public List<Treatment> searchTreatments(SearchOptions options) throws AuthorizationException, NightscoutException {

        Call<List<Treatment>> call = super.getRetrofit().create(Treatments.class).searchTreatments(options == null ? Collections.emptyMap() : options.get());
        try {
            Response<List<Treatment>> response = call.execute();
            return getTreatmentsFromResponse(response);

        } catch (IOException e) {
            throw new NightscoutException(e);
        }
    }



    public void syncTreatments(final SearchResultListener listener, Long lastModified, Integer limit, String fields) throws NightscoutException {

        Call<List<Treatment>> call = super.getRetrofit().create(Treatments.class).syncTreatments(lastModified, limit, fields);
        call.enqueue(getCallback(listener));

    }

    public void searchTreatments(SearchResultListener cb, SearchOptions options) throws NightscoutException {

        Call<List<Treatment>> call = super.getRetrofit().create(Treatments.class).searchTreatments(options == null ? Collections.emptyMap() : options.get());
        call.enqueue(getCallback(cb));
    }

    public String addTreatment(Treatment treatment) throws NightscoutException {
        Treatments service = super.getRetrofit().create(Treatments.class);
        Call<Void> call = service.putTreatment(treatment);
        Response<Void> response;
        try {
            response = call.execute();
            return returnLocation(response);
        } catch (Exception e) {
            throw new NightscoutException(e);
        }
    }



    private Callback<List<Treatment>> getCallback(SearchResultListener listener) {
        return new Callback<List<Treatment>>() {
            @Override
            public void onResponse(Call<List<Treatment>> call, Response<List<Treatment>> response) {
                try {
                    listener.onTreatment(getTreatmentsFromResponse(response));
                } catch (AuthorizationException | NightscoutException e) {
                    this.onFailure(call, e);
                }
            }

            @Override
            public void onFailure(Call<List<Treatment>> call, Throwable t) {
                listener.onFailure(t);
            }
        };
    }
    private List<Treatment> getTreatmentsFromResponse(Response<List<Treatment>> response) throws AuthorizationException, NightscoutException {
        if (response.isSuccessful()) {
            return response.body();
        } else if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
            throw new AuthorizationException(response.message());
        } else {
            throw new NightscoutException(response);
        }
    }
}
