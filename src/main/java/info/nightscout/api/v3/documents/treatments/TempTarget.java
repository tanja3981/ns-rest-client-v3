package info.nightscout.api.v3.documents.treatments;

import info.nightscout.api.v3.documents.Treatment;

import static info.nightscout.api.v3.documents.EventType.TEMP_TARGET;

public class TempTarget extends Treatment {
    public String reason;
    public Double targetBottom;
    public Double targetTop;

    public TempTarget() {
        this.eventType = TEMP_TARGET;
    }
}
