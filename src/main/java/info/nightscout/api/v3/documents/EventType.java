package info.nightscout.api.v3.documents;

public interface EventType {

    String BG = "BG Check";
    String MEAL = "Meal Bolus";
    String SNACK = "Snack Bolus";
    String CORRECTION = "Correction Bolus";
    String COMBO_BOLUS = "Combo Bolus";
    String CARB_CORRECTON = "Carb Correction";
    String WIZARD = "Bolus Wizard";
    String SENSOR_START = "Sensor Start";
    String SENSOR_CHANGE = "Sensor Change";
    String INSULIN_CHANGE = "Insulin Change";
    String SITE_CHANGE = "Site Change";
    String TEMP_TARGET = "Temporary Target";
    String PBAT_CHANGE = "Pump Battery Change";
    String TEMP_BASAL = "Temp Basal";
    String EXERCISE = "Exercise";
    String NOTE = "Note";
    String OPENAPS_OFFLINE = "OpenAPS Offline";
    String PROFILE_SWITCH = "Profile Switch";
    String ANNOUNCEMENT = "Announcement";

}
