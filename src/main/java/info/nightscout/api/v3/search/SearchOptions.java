package info.nightscout.api.v3.search;

import com.sun.istack.internal.NotNull;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Options for retrieving documents.
 */
public class SearchOptions {
    private final Map<String, String> map;

    private SearchOptions() {
        this.map = new HashMap<String, String>();
    }

    public static SearchOptions create() {
        return new SearchOptions();
    }


    public Map<String, String> get() {
        return map;
    }

    /**
     * Adds one or another filter.
     * Can be called repeat
     *
     * @param filter
     * @param expression
     * @return
     */
    public SearchOptions filter(@NotNull Filter filter, String expression) {
        if (filter != null && !StringUtils.isEmpty(expression)) {
            String filterExpr = map.getOrDefault("filter", "");
            if (!filterExpr.isEmpty()) {
                filterExpr = filterExpr.concat("&");
            }
            filterExpr = filterExpr.concat(filter.value).concat("=").concat(expression);
            this.map.put("filter", filterExpr);
        }
        return this;
    }

    public SearchOptions limit(Integer limit) {
        if (limit != null) {
            this.map.put("limit", limit.toString());
        }
        return this;
    }

    public SearchOptions skip(Integer skip) {
        if (skip != null) {
            this.map.put("skip", skip.toString());
        }
        return this;
    }

    public SearchOptions sortBy(String sortBy) {
        if (!StringUtils.isEmpty(sortBy)) {
            this.map.put("sort", sortBy);
        }
        return this;
    }

    public SearchOptions sortDesc(String sortDesc) {
        if (!StringUtils.isEmpty(sortDesc)) {
            this.map.put("sort_desc", sortDesc);
        }
        return this;
    }

    public SearchOptions returnCustomFields(String fields) {
        if (!StringUtils.isEmpty(fields)) {
            String fieldsExpr = map.getOrDefault("fields", "");
            if (!fieldsExpr.isEmpty()) {
                fieldsExpr = fieldsExpr.concat(",");
            }
            fieldsExpr = fieldsExpr.concat(fields);
            this.map.put("fields", fieldsExpr);
        }
        return this;
    }

    public SearchOptions returnAllFields() {
        this.map.put("fields", "_all");
        return this;
    }

    public SearchOptions returnClientFields() {
        this.map.put("fields", "_client");
        return this;
    }


}
