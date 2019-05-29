package info.nightscout.api.v3.documents.treatments;

import info.nightscout.api.v3.documents.EventType;
import info.nightscout.api.v3.documents.Treatment;

public class TempBasal extends Treatment {
    //added
    public Number duration; //for temp basals
    public Number percent; //for percentage basals
    public Number rate; //for abs basals
    public Boolean isFakedTempBasal;

    public TempBasal() {
        super();
        this.eventType = EventType.TEMP_BASAL;
    }
}
