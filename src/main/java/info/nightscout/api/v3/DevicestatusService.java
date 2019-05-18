package info.nightscout.api.v3;

import info.nightscout.api.v3.documents.Devicestatus;
import info.nightscout.api.v3.documents.Treatment;
import info.nightscout.api.v3.err.AuthorizationException;
import info.nightscout.api.v3.err.NightscoutException;
import info.nightscout.api.v3.rest.DeviceStatus;
import info.nightscout.api.v3.rest.Treatments;
import info.nightscout.api.v3.search.SearchOptions;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Collections;
import java.util.List;


public class DevicestatusService extends NightscoutService {

    public DevicestatusService(String baseUrl, String token) {
        super(baseUrl, token);
    }

    /**
     * Search in DeviceStatus collection without any filter.
     * A limit to 100 entries is applied.
     *
     * @return A list of {@link DeviceStatus} items.
     * @throws {@link AuthorizationException}, {@link NightscoutException}
     */
    public List<Devicestatus> searchDevicestatus() throws AuthorizationException, NightscoutException {
        SearchOptions options = SearchOptions.create().limit(100);
        return this.searchDevicestatus(options);
    }

    /**
     * Search in DeviceStatus collection.
     *
     * @param options The filter and ordering options.
     * @return A list of {@link DeviceStatus} items.
     * @throws IOException
     */
    public List<Devicestatus> searchDevicestatus(SearchOptions options) throws AuthorizationException, NightscoutException {

        Call<List<Devicestatus>> call = super.getRetrofit().create(DeviceStatus.class).searchDevicestatus(options == null ? Collections.emptyMap() : options.get());
        try {
            Response<List<Devicestatus>> response = call.execute();
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

    public String addDevicestatus(Devicestatus devicestatus) throws AuthorizationException, NightscoutException {
        DeviceStatus service = super.getRetrofit().create(DeviceStatus.class);
        Call<Void> call = service.putDevicestatus(devicestatus);
        Response<Void> response;
        try {
            response = call.execute();
            return returnLocation(response);
        } catch (Exception e) {
            throw new NightscoutException(e);
        }
    }


}
