package info.nightscout.api.v3;

import info.nightscout.api.v3.documents.Treatment;
import info.nightscout.api.v3.err.AuthorizationException;
import info.nightscout.api.v3.err.NightscoutException;
import info.nightscout.api.v3.rest.Treatments;
import info.nightscout.api.v3.search.SearchOptions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Collections;
import java.util.List;


public class TreatmentsService extends NightscoutService {

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
     * @param options
     * @return a list of treatments
     * @throws {@link AuthorizationException}, {@link NightscoutException}
     */
    public List<Treatment> searchTreatments(SearchOptions options) throws AuthorizationException, NightscoutException {

        Call<List<Treatment>> call = super.getRetrofit().create(Treatments.class).searchTreatments(options == null ? Collections.emptyMap() : options.get());
        try {
            Response<List<Treatment>> response = call.execute();
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

    public void searchTreatments(Callback<List<Treatment>> cb, SearchOptions options) throws AuthorizationException, NightscoutException {

        Call<List<Treatment>> call = super.getRetrofit().create(Treatments.class).searchTreatments(options == null ? Collections.emptyMap() : options.get());
        call.enqueue(cb);
    }

    public String addTreatment(Treatment treatment) throws AuthorizationException, NightscoutException {
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


}
