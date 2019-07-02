package info.nightscout.api.v3.search;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Options for retrieving documents.
 */
public class SearchOptions {
    private final Map<String, String> map;

    private SearchOptions() {
        this.map = new HashMap<>();
    }

    public static SearchOptions create() {
        return new SearchOptions();
    }


    public Map<String, String> get() {
        return map;
    }

    /**
     * Adds another filter.
     * Can be called repeatedly to add multiple filters.
     *
     * @param filter
     * @param expression
     * @return Search options
     */
    public SearchOptions filter(final String field, final Filter filter, final String expression) {
        if (!StringUtils.isEmpty(field) && filter != null && !StringUtils.isEmpty(expression)) {
            String filterExpr = field.concat("$").concat(filter.value);
            this.map.put(filterExpr, expression);
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
            this.map.put("sort$desc", sortDesc);
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
