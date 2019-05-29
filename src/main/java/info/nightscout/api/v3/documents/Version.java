package info.nightscout.api.v3.documents;

public class Version {
    public String version;
    public String apiVersion;
    public Number srvDate;
    public String srvDateString;
    public Storage storage;

}

class Storage {
    String type;
    String version;
    Number srvDate;
    String srvDateString;
}