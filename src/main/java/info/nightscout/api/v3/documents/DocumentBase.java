package info.nightscout.api.v3.documents;

public abstract class DocumentBase {
    //required
    String identifier;
    Integer date;
    String app;

    String dateString;
    String device;
    String user;
    String _id;
    Integer srvCreated;
    String srvCreatedBy;
    Integer srvModified;
    String srvModifiedBy;
    Boolean isValid;

}
