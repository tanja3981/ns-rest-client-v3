package info.nightscout.api.v3.search;

import info.nightscout.api.v3.NightscoutService;

/**
 *
 */
public abstract class SearchService extends NightscoutService {
    public static final String FILTER = "filter_parameters";
    public static final String SORT = "sort";
    public static final String SORTDESC = "sort_desc";
    public static final String LIMIT = "limit";
    public static final String SKIP = "skip";
    public static final String FIELDS = "fields";

    public SearchService(final String baseUrl, final String authToken) {
        super(baseUrl, authToken);
    }




}
