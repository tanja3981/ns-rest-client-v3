package info.nightscout.api.v3.documents.treatments;

import info.nightscout.api.v3.documents.Treatment;

import static info.nightscout.api.v3.documents.EventType.COMBO_BOLUS;

public class ExtendedBolus extends Treatment {
    public Number splitNow;
    public Number splitExt;
    public Number enteredInsulin;
    public Number relative;
    public Number enteredinsulin;

    public ExtendedBolus() {
        super();
        super.eventType = COMBO_BOLUS;
    }
}
