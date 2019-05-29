package info.nightscout.api.v3.documents;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class Profile extends DocumentBase {

    public Date startDate;
    public String defaultProfile;
    /**
     * Units of BG measurement: mg/dl or mmol
     */
    public String units;
    public Date created_at;
    public Long mills;

    public Map<String, ProfileEntry> store;


    public class ProfileEntry {
        public Date startDate;
        public int dia;
        public List<TimeSlot> carbratio;
        public int carbs_hr;//carb activity, absorption rate
        public int delay;  //not used anymore from cgm-remote-monitor UI
        public List<TimeSlot> sens;
        public String timezone;
        public List<TimeSlot> basal;
        public List<TimeSlot> target_low;
        public List<TimeSlot> target_high;
    }

    public class TimeSlot {
        public String time;
        public Number value;
        public Number timeAsSeconds;
    }
}