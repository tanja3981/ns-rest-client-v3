package info.nightscout.api.v3.documents;

public class DocumentBase {
    //required
    public String identifier;
    public Long date;
    public String app;

    //optional
    public String dateString;
    public String device;
    public String user;
    public String _id;
    public Long srvCreated;
    public String srvCreatedBy;
    public Long srvModified;
    public String srvModifiedBy;
    public Boolean isValid;

}
