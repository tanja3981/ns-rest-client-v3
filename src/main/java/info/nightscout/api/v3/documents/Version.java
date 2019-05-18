package info.nightscout.api.v3.documents;

public class Version {
     private String version;
     private String apiVersion;
     private Number srvDate;
     private String srvDateString;

     public String getVersion() {
          return version;
     }

     public void setVersion(String version) {
          this.version = version;
     }

     public String getApiVersion() {
          return apiVersion;
     }

     public void setApiVersion(String apiVersion) {
          this.apiVersion = apiVersion;
     }

     public Number getSrvDate() {
          return srvDate;
     }

     public void setSrvDate(Number srvDate) {
          this.srvDate = srvDate;
     }

     public String getSrvDateString() {
          return srvDateString;
     }

     public void setSrvDateString(String srvDateString) {
          this.srvDateString = srvDateString;
     }
}
