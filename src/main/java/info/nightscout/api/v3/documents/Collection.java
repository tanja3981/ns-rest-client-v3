package info.nightscout.api.v3.documents;

/**
 * Available collections.
 */
public enum Collection {
    DEVICESTATUS("devicestatus"),
    ENTRIES("entries"),
    FOOD("food"),
    PROFILE("profile"),
    SETTINGS("settings"),
    TREATMENTS("treatments");

    private final String value;

    private Collection(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }


}
