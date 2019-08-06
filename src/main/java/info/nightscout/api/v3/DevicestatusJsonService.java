package info.nightscout.api.v3;

import com.google.gson.JsonObject;
import info.nightscout.api.v3.err.AuthorizationException;
import info.nightscout.api.v3.err.NightscoutException;
import info.nightscout.api.v3.rest.DeviceStatus;
import info.nightscout.api.v3.rest.DeviceStatusJson;
import info.nightscout.api.v3.search.SearchOptions;
import info.nightscout.api.v3.search.SearchResultListener;
import info.nightscout.api.v3.search.SearchService;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Collections;
import java.util.List;


public class DevicestatusJsonService extends SearchService {

    public DevicestatusJsonService(String baseUrl, String token) {
        super(baseUrl, token);
    }

    /**
     * Search in DeviceStatus collection without any filter.
     * A limit to 100 entries is applied.
     *
     * @return A list of {@link DeviceStatus} items.
     * @throws {@link AuthorizationException}, {@link NightscoutException}
     */
    public String searchDevicestatus() throws AuthorizationException, NightscoutException {
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
    public String searchDevicestatus(SearchOptions options) throws AuthorizationException, NightscoutException {

        Retrofit retrofit = getRetrofit(ScalarsConverterFactory.create());
        Call<String> call = retrofit.create(DeviceStatusJson.class).searchDevicestatus(options == null ? Collections.emptyMap() : options.get());
        try {
            Response<String> response = call.execute();
            if (response.isSuccessful()) {
                if (response.body() != null) {
                    String jsonresponse = response.body();
                    return jsonresponse;
                }
                return "";
            } else if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                throw new AuthorizationException(response.message());
            } else {
                throw new NightscoutException(response);
            }
        } catch (IOException e) {
            throw new NightscoutException(e);
        }

    }

    public void syncDevicestatus(SearchOptions options, SearchResultListener listener) throws AuthorizationException, NightscoutException {
        Retrofit retrofit = getRetrofit(ScalarsConverterFactory.create());
        Call<String> call = retrofit.create(DeviceStatusJson.class).searchDevicestatus(options == null ? Collections.emptyMap() : options.get());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body() != null) {
                    String jsonresponse = response.body().toString();
                    listener.onDeviceStatus(jsonresponse);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                listener.onFailure(t);
            }
        });
    }

    public String addDevicestatus(String devicestatusJson) throws AuthorizationException, NightscoutException {
        DeviceStatusJson service = super.getRetrofit().create(DeviceStatusJson.class);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),devicestatusJson);
        Call<Void> call = service.putDevicestatus(body);
        Response<Void> response;
        try {
            response = call.execute();
            return returnLocation(response);
        } catch (Exception e) {
            throw new NightscoutException(e);
        }
    }


}
