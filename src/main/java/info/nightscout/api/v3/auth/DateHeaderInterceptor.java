package info.nightscout.api.v3.auth;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Interceptor to add the mandatory date header to each request.
 */
public class DateHeaderInterceptor implements Interceptor {

    public static SimpleDateFormat FORMAT = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder builder = original.newBuilder()
                .header("Date", getDateString());

        Request request = builder.build();
        return chain.proceed(request);
    }

    private String getDateString() {

        return FORMAT.format(new Date());
    }
}
