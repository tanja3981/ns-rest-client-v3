package info.nightscout.api.v3;

import info.nightscout.api.v3.auth.DateHeaderInterceptor;
import info.nightscout.api.v3.auth.TokenInterceptor;
import info.nightscout.api.v3.documents.Version;
import info.nightscout.api.v3.rest.GetVersion;
import okhttp3.OkHttpClient;
import org.apache.commons.lang3.StringUtils;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class NightscoutService {

    protected final String url;
    protected final String authToken;

    public NightscoutService(final String baseUrl, final String authToken) {
        this.url = baseUrl;
        this.authToken = authToken;
    }

    public Version getVersion() throws IOException {

        Retrofit retrofit = getRetrofit();
        GetVersion service = retrofit.create(GetVersion.class);

        Call<Version> call = service.getVersion();
        Response<Version> response = call.execute();
        return response.body();
    }

    protected Retrofit getRetrofit() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(url);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        addTokenInterceptor(httpClient);

        addDateHeader(httpClient);

        builder.client(httpClient.build());
        return builder.build();
    }

    private void addDateHeader(OkHttpClient.Builder httpClient) {
        DateHeaderInterceptor dateHeaderInterceptor = new DateHeaderInterceptor();
        if (!httpClient.interceptors().contains(dateHeaderInterceptor)) {
            httpClient.addInterceptor(dateHeaderInterceptor);

        }
    }

    private void addTokenInterceptor(OkHttpClient.Builder httpClient) {
        TokenInterceptor interceptor = new TokenInterceptor(authToken);
        if (!httpClient.interceptors().contains(interceptor)) {
            httpClient.addInterceptor(interceptor);
        }
    }




}
