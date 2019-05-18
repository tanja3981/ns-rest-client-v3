package info.nightscout.api.v3.err;


/**
 *
 */
public class NightscoutException extends Exception {

    /**
     * @param response
     */
    public NightscoutException(okhttp3.Response response) {
        super(String.format("%s %s", response.code(), response.message()));
    }

    public NightscoutException(retrofit2.Response response) {
        super(String.format("%s %s", response.code(), response.message()));
    }

    public NightscoutException(Exception e) {
        super(e);
    }
}
