package info.nightscout.api.v3.search;

public enum Filter {
    EQUALS("eq"),
    NOT_EQUALS("ne"),
    GREATER_THAN("gt"),
    GREATER_THAN_EQUALS("gte"),
    LOWER_THAN("lt"),
    LOWER_THAN_EQUALS("lte"),
    IN("in"),
    NOT_IN("nin"),
    REGEX("re");
    public final String value;

    private Filter(String value) {
        this.value = value;
    }

}
