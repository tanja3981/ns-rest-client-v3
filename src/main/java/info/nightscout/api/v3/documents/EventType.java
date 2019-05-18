package info.nightscout.api.v3.documents;

public enum EventType {

    BG("BG Check"),
    MEAL("Meal Bolus"),
    SNACK("Snack Bolus"),
    CORRECTION("Correction Bolus"),
    COMBO_BOLUS("Combo Bolus"),
    CARB_CORRECTON("Carb Correction"),
    WIZARD("Bolus Wizard"),
    SENSOR_START("Sensor Start"),
    SENSOR_CHANGE("Sensor Change"),
    INSULIN_CHANGE("Insulin Change"),
    SITE_CHANGE("Site Change"),
    TEMP_TARGET("Temporary Target"),
    PBAT_CHANGE("Pump Battery Change"),
    TEMP_BASAL("Temp Basal"),
    EXERCISE("Exercise"),
    NOTE("Note"),
    OPENAPS_OFFLINE("OpenAPS Offline"),
    PROFILE_SWITCH("Profile Switch"),
    ANNOUNCEMENT("Announcement");
    private String value;

    EventType(String value) {
        this.value = value;
    }
}
