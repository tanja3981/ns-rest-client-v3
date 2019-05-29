package info.nightscout.api.v3.documents;

public class Status {
    public String version;
    public String apiVersion;
    public Storage storage;
    public ApiPermissions apiPermissions;

    public class ApiPermissions {
        public String devicestatus;
        public String entries;
        public String food;
        public String profile;
        public String treatments;

    }
}