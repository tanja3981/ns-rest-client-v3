package info.nightscout.api.v3.documents;

import com.google.gson.Gson;

public class Treatment extends DocumentBase {
    public String eventType;
    public String created_at;
    public String glucose;
    public String glucoseType;
    public String units;
    public Number carbs;
    public Number carbTime;
    public Number insulin;
    public String notes;
    public String enteredBy;
    public Number duration;
    public Boolean isSMB;
    public String boluscalcJson;

    public void setBolusCalcJson(String json) {
        this.boluscalcJson = json;
        //Gson gson = new Gson();
        //boluscalc = gson.fromJson(json, BolusCalc.class);
    }

    /*class BolusCalc {
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

    }*/
}
