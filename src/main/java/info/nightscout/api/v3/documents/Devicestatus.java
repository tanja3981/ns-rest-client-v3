package info.nightscout.api.v3.documents;

public class Devicestatus extends DocumentBase {


}
//
//class Pump {
//    public Battery battery;
//    public PumpStatus status;
//    public ExtendedDevicestatus extended;
//    public Number reservoir;
//    public Date clock;
//}
//
//class PumpStatus {
//    public String status;
//    public Date timestamp;
//}
//
//class Battery {
//    public Number percent;
//}
//
//class ExtendedDevicestatus {
//    public String version;
//    public Number pumpIob;
//    public Date lastBolus;
//    public Number lastBolusAmount;
//    public Number tempBasalAbsoluteRate;
//    public Date tempBasalStart;
//    public Number tempBasalRemaining;
//    public Number baeBasalRate;
//    public String activeProfile
//}
//{
//
//        "device": "openaps://Google Pixel 2 XL",
//        "pump": {
//            "battery": {
//                "percent": 75
//            },
//            "status": {
//                "status": "normal",
//                "timestamp": "2019-04-26T01:47:48Z"
//            },
//            "extended": {
//                "Version": "2.2.2-c2cf6d100-2019.04.07-20:26",
//                "PumpIOB": 0.01,
//                "LastBolus": "26.04.19 00:20:16 Mitteleuropäische Sommerzeit",
//                "LastBolusAmount": 0.1,
//                "TempBasalAbsoluteRate": 0,
//                "TempBasalStart": "26.04.19 03:47",
//                "TempBasalRemaining": 55,
//                "BaseBasalRate": 0.66,
//                "ActiveProfile": "2019/04/01 Fiasp 15.7"
//             },
//            "reservoir": 169,
//            "clock": "2019-04-26T01:52:46Z"
//        },
//        "openaps": {
//            "suggested": {
//                "temp": "absolute",
//                "bg": 141,
//                "tick": -11,
//                "eventualBG": 73,
//                "insulinReq": 0,
//                "deliverAt": "2019-04-26T01:52:46.265Z",
//                "sensitivityRatio": 1.2,
//                "predBGs": {
//                    "IOB": [
//                        141,
//                        131,
//                        121,
//                        112,
//                        103,
//                        96,
//                        88,
//                        82,
//                        76,
//                        71,
//                        67,
//                        64,
//                        61,
//                        58,
//                        56,
//                        54,
//                        51,
//                        49,
//                        47,
//                        46,
//                        44,
//                        42,
//                        41,
//                        39
//                    ],
//                    "ZT": [
//                        141,
//                        138,
//                        134,
//                        131,
//                        128,
//                        125,
//                        122,
//                        119,
//                        116,
//                        113,
//                        111,
//                        109,
//                        107,
//                        105,
//                        104,
//                        103,
//                        102,
//                        101,
//                        100,
//                        100,
//                        100,
//                        100,
//                        100,
//                        100,
//                        101,
//                        102,
//                        102
//                    ]
//                },
//        "COB": 0,
//        "IOB": 1.432,
//        "reason": "COB: 0, Dev: 4, BGI: -3, ISF: 50, CR: 15, Target: 90, minPredBG 70, minGuardBG 26, IOBpredBG 39; minGuardBG 26<60",
//        "duration": 120,
//        "rate": 0,
//        "timestamp": "2019-04-26T01:52:46Z"
//        },
//        "iob": {
//        "iob": 1.432,
//        "basaliob": 1.428,
//        "activity": 0.0129,
//        "time": "2019-04-26T01:52:46Z"
//        }
//        },
//        "uploaderBattery": 100,
//        "created_at": "2019-04-26T01:52:46Z",
//        "NSCLIENT_ID": 1556243566307
//        }