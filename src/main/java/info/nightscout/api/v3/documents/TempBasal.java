package info.nightscout.api.v3.documents;

public class TempBasal extends Treatment {
    //added
    public Number duration; //for temp basals
    public Double percent; //for temp basals
    public Double rate; //for temp basals

    public TempBasal() {
        super();
        this.eventType = EventType.TEMP_BASAL.toString();
    }
}
