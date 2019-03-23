package info.nightscout.api.v3.documents;

public class Entry extends DocumentBase implements Document {
    String type;
    Number sgv;
    String direction;
    Number noise;
    Number filtered;
    Number unfiltered;
    Number rssi;
    String units;
}
