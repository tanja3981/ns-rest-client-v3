package info.nightscout.api.v3.documents;

import java.util.Date;

public class Profile extends DocumentBase {

    Date startDate;
    String defaultProfile;
    String units;
    Date created_at;
    Long mills;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getDefaultProfile() {
        return defaultProfile;
    }

    public void setDefaultProfile(String defaultProfile) {
        this.defaultProfile = defaultProfile;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Long getMills() {
        return mills;
    }

    public void setMills(Long mills) {
        this.mills = mills;
    }
}
