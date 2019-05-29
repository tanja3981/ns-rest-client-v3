package info.nightscout.api.v3.documents.treatments;

import info.nightscout.api.v3.documents.EventType;
import info.nightscout.api.v3.documents.Treatment;

public class ProfileSwitch extends Treatment {
    public String profile;
    public String profileJson;
    public String profilePlugin;
    public boolean CircadianPercentageProfile;
    public int timeshift;
    public int percentage;

public ProfileSwitch() {
    super();
    this.eventType = EventType.PROFILE_SWITCH;
}
}
