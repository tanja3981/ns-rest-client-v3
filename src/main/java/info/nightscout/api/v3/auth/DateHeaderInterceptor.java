package info.nightscout.api.v3.auth;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request original = chain.request();


        Request.Builder builder = original.newBuilder()
                .header("Date", getDateString());

        Request request = builder.build();
        return chain.proceed(request);
    }

    public static SimpleDateFormat FORMAT = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");

    private String getDateString() {
        return FORMAT.format(new Date());
    }
}
