package info.nightscout.api.v3;

import info.nightscout.api.v3.documents.Treatment;
import info.nightscout.api.v3.err.AuthorizationException;
import info.nightscout.api.v3.err.NightscoutException;
import info.nightscout.api.v3.rest.Search;
import info.nightscout.api.v3.rest.Treatments;
import info.nightscout.api.v3.search.SearchOptions;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Collections;
import java.util.List;

/**
 *
 */
public class SearchService extends NightscoutService {
    public static final String FILTER = "filter_parameters";
    public static final String SORT = "sort";
    public static final String SORTDESC = "sort_desc";
    public static final String LIMIT = "limit";
    public static final String SKIP = "skip";
    public static final String FIELDS = "fields";

    public SearchService(final String baseUrl, final String authToken) {
        super(baseUrl, authToken);
    }

    private Search getService() throws NightscoutException {
        Retrofit retrofit = super.getRetrofit();
        return retrofit.create(Search.class);

    }


}
