package info.nightscout.api.v3.documents;

/**
 * Available collections.
 */
public enum Collections {
    DEVICESTATUS("devicestatus"),
    ENTRIES("entries"),
    FOOD("food"),
    PROFILE("profile"),
    SETTINGS("settings"),
    TREATMENTS("treatments");

    private final String value;

    private Collections(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }


}
