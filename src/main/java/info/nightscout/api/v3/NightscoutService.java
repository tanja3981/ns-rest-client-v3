package info.nightscout.api.v3;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import info.nightscout.api.v3.adapter.TreatmentAdapter;
import info.nightscout.api.v3.auth.DateHeaderInterceptor;
import info.nightscout.api.v3.auth.TokenInterceptor;
import info.nightscout.api.v3.documents.LastModifiedResult;
import info.nightscout.api.v3.documents.Status;
import info.nightscout.api.v3.documents.Treatment;
import info.nightscout.api.v3.documents.Version;
import info.nightscout.api.v3.err.AuthorizationException;
import info.nightscout.api.v3.err.NightscoutException;
import info.nightscout.api.v3.rest.Nightscout;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import org.apache.commons.lang3.StringUtils;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

import javax.net.ssl.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.security.cert.CertificateException;
import java.text.DateFormat;

import static retrofit2.converter.gson.GsonConverterFactory.create;

/**
 *
 */
public class NightscoutService {
    protected Boolean disableSSL = Boolean.FALSE;

    protected final String url;
    protected final String authToken;

    public NightscoutService(final String baseUrl, final String authToken) {
        this.url = baseUrl;
        this.authToken = authToken;
    }

    public Status getStatus() throws AuthorizationException, NightscoutException {
        Retrofit retrofit = getRetrofit();
        Nightscout service = retrofit.create(Nightscout.class);

        Call<Status> call = service.getStatus();
        try {
            Response<Status> response = call.execute();
            checkResponse(response);
            return response.body();

        } catch (IOException e) {
            throw new NightscoutException(e);
        }
    }

    public Version getVersion() throws NightscoutException {

        Retrofit retrofit = getRetrofit();
        Nightscout service = retrofit.create(Nightscout.class);

        Call<Version> call = service.getVersion();
        try {
            Response<Version> response = call.execute();
            checkResponse(response);
            return response.body();
        } catch (Exception e) {
            throw new NightscoutException(e);
        }
    }

    public LastModifiedResult getLastModified() throws AuthorizationException, NightscoutException {
        Retrofit retrofit = getRetrofit();
        Nightscout service = retrofit.create(Nightscout.class);

        Call<LastModifiedResult> call = service.getLastModified();
        try {
            Response<LastModifiedResult> response = call.execute();
            checkResponse(response);
            return response.body();

        } catch (IOException e) {
            throw new NightscoutException(e);
        }
    }

    public Retrofit getRetrofit() throws NightscoutException {
        if (StringUtils.isEmpty(url)) {
            throw new NightscoutException("Missing Nightscout URL");
        }
        try {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Treatment.class, new TreatmentAdapter())
                    .enableComplexMapKeySerialization()
                    .serializeNulls()
                    .setDateFormat(DateFormat.LONG)
                    .setPrettyPrinting()
                    .create();

            Retrofit.Builder builder = new Retrofit.Builder()
                    .addConverterFactory(create(gson))
                    .addConverterFactory(create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(url);

            OkHttpClient.Builder httpClient = getHttpClient();

            addTokenInterceptor(httpClient);
            addDateHeader(httpClient);

            builder.client(httpClient.build());
            return builder.build();
        } catch (Exception e) {
            throw new NightscoutException(e);
        }
    }

    private OkHttpClient.Builder getHttpClient() throws Exception {
        if (disableSSL.booleanValue()) {
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            return builder;

        } else {
            return new OkHttpClient.Builder();
        }
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

    protected String returnLocation(Response response) throws AuthorizationException, NightscoutException {
        checkResponse(response);

        if (response.isSuccessful()) {
            Headers headers = response.headers();
            final String id = headers.get("Location");
            return id;
        } else {
            throw new NightscoutException(response);
        }
    }

    public Response checkResponse(Response response) throws AuthorizationException, NightscoutException {
        if (!response.isSuccessful()) {
            if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                throw new AuthorizationException(response.message());
            } else {
                throw new NightscoutException(response);
            }
        }
        return response;
    }

}
