package info.nightscout.api.v3.auth;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class LogInterceptor implements Interceptor {
    private Logger logger = LoggerFactory.getLogger(LogInterceptor.class);

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();
        String escapedUrl = originalHttpUrl.toString();




        logger.info("Request URL: " + originalHttpUrl.url().toString());


        return chain.proceed(original);
    }
}
