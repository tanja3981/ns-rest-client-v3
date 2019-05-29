package info.nightscout.api.v3.documents;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.Map;

public class Treatment extends DocumentBase {
    public EventType eventType;
    public String created_at;
    public String glucose;
    public String glucoseType;
    public String units;
    public Integer carbs;
    public Integer carbTime;
    public Double insulin;
    public String notes;
    public String enteredBy;
    public Integer duration;
    public Boolean isSMB;
    public BolusCalc boluscalc;

    public void setBolusCalc(String json) {
        Gson gson = new Gson();
        boluscalc = gson.fromJson(json, BolusCalc.class);
    }

    class BolusCalc {
        public String eventTime;
        public double targetBGLow;
        public double targetBGHigh;
        public double isf;
        public double ic;
        public double iob;
        public boolean bolusiobused;
        public boolean basaliobused;
        public double bg;
        public double insulinbg;
        public boolean insulinbgused;
        public double bgdiff;
        public double insulincarbs;
        public int carbs;
        public double othercorrection;
        public double insulintrend;
        public double insulin;

    }
}
