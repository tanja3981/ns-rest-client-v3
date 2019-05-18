package info.nightscout.api.v3.documents;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class DocumentBase {

    public static SimpleDateFormat FORMAT = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);

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

    public DocumentBase() {
        this.date = System.currentTimeMillis();
        this.dateString = FORMAT.format(this.date);
    }

}
