package info.nightscout.api.v3.documents;

import com.google.gson.Gson;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DocumentBase implements Serializable {

    private static SimpleDateFormat FORMAT = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);

    //required
    public String identifier;
    public Long date;
    public String app;

    //optional
    public String dateString;
    public String device;
    public String user;
    public Long srvCreated;
    public String srvCreatedBy;
    public Long srvModified;
    public String srvModifiedBy;
    public Boolean isValid;

    public Map<String, Object> fields = new HashMap<>();

    public DocumentBase() {
        this.date = System.currentTimeMillis();
        this.dateString = FORMAT.format(this.date);
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
