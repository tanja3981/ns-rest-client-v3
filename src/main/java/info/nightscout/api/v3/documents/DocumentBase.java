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

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Integer getSrvCreated() {
        return srvCreated;
    }

    public void setSrvCreated(Integer srvCreated) {
        this.srvCreated = srvCreated;
    }

    public String getSrvCreatedBy() {
        return srvCreatedBy;
    }

    public void setSrvCreatedBy(String srvCreatedBy) {
        this.srvCreatedBy = srvCreatedBy;
    }

    public Integer getSrvModified() {
        return srvModified;
    }

    public void setSrvModified(Integer srvModified) {
        this.srvModified = srvModified;
    }

    public String getSrvModifiedBy() {
        return srvModifiedBy;
    }

    public void setSrvModifiedBy(String srvModifiedBy) {
        this.srvModifiedBy = srvModifiedBy;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }
}
