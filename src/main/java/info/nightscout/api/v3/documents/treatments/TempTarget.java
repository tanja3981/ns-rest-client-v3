package info.nightscout.api.v3.documents.treatments;

import info.nightscout.api.v3.documents.EventType;
import info.nightscout.api.v3.documents.Treatment;

public class TempTarget extends Treatment {
    public String reason;
    public Double targetBottom;
    public Double targetTop;

    public TempTarget() {
        this.eventType = EventType.TEMP_TARGET;
    }
}
