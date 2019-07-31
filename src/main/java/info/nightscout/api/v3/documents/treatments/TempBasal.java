package info.nightscout.api.v3.documents.treatments;

import info.nightscout.api.v3.documents.Treatment;

import static info.nightscout.api.v3.documents.EventType.TEMP_BASAL;

public class TempBasal extends Treatment {
    //added
    public Number duration; //for temp basals
    public Number percent; //for percentage basals
    public Number rate; //for abs basals
    public Boolean isFakedTempBasal;

    public TempBasal() {
        super();
        this.eventType = TEMP_BASAL;
    }
}
