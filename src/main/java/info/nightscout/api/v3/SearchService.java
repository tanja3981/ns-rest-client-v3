package info.nightscout.api.v3;

import info.nightscout.api.v3.documents.Treatment;
import info.nightscout.api.v3.rest.Search;
import info.nightscout.api.v3.search.SearchOptions;
import org.apache.commons.lang3.StringUtils;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.*;

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

    private Search getService() {
        Retrofit retrofit = super.getRetrofit();
        return retrofit.create(Search.class);

    }

    /**
     * Search in treatments collection without any filter.
     *
     * @return
     * @throws IOException
     */
    public List<Treatment> searchTreatments() throws IOException {
        return this.searchTreatments(null);
    }

    /**
     * Search in treatments collection.
     *
     * @param options
     * @return
     * @throws IOException
     */
    public List<Treatment> searchTreatments(SearchOptions options) throws IOException {

        //String date = Search.dateFormatter.format(new Date());

        Call<List<Treatment>> call = getService().searchTreatments( options == null ? Collections.emptyMap() : options.get());
        Response<List<Treatment>> response = call.execute();
        if (response.isSuccessful()) {
            return response.body();
        } else {
            System.out.println(response.code());
            System.out.println(response.message());
            return null;
        }
    }

}
