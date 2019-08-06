package info.nightscout.api.v3.documents;

import java.util.Date;
import java.util.List;

public class Devicestatus extends DocumentBase {

    public Number uploaderBattery;
    public String created_at;
    @Deprecated
    public Long mills;

    public Pump pump;
    public OpenAPS openaps;

    public class OpenAPS {
        public Suggested suggested;
        public Enacted enacted;
        public IOB iob;

        public class Enacted {
            public String timestamp;
            public String reason;
        }

        public class Suggested {
            public String temp;
            public Double bg;
            public String tick;
            public Double eventualBG;
            public Double snoozeBG;

            public Prediction predBGs;

            public Double cob;
            public Double iob;
            public String reason;
            public Number duration;
            public Double rate;

            public String timestamp;
            public Double insulinReq;
            public Date deliverAt;
            public Double sensitivityRatio;

        }

        public class IOB {
            public Double iob;
            public Double basaliob;
            public Double activity;
            public Date time;
        }

        public class Prediction {
            public List<Double> iob;
            public List<Double> zt;
            public List<Double> cob;
            public List<Double> uam;

        }
    }

    public class Pump {
        public String clock;
        public Double reservoir;
        public Battery battery;
        public Status status;
        public Extended extended;

        public class Battery {
            public Integer percent;
            public Double voltage;
        }

        public class Status {
            public String status;
            public Date timestamp;
        }

        public class Extended {
            public String Version;
            public Double pumpIOB;
            public Date lastBolus;
            public Double lastBolusAmount;
            public Double baseBasalRate;

            public Double tempBasalAbsoluteRate;
            public Date tempBasalStart;
            public Number tempBasalRemaining;

            public String activeProfile;
        }
    }
}