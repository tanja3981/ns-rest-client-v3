package info.nightscout.api.v3.search;

import info.nightscout.api.v3.documents.Devicestatus;
import info.nightscout.api.v3.documents.Entry;
import info.nightscout.api.v3.documents.Profile;
import info.nightscout.api.v3.documents.Treatment;

import java.util.List;

public interface SearchResultListener {

    void onEntry(List<Entry> bgs);

    void onTreatment(List<Treatment> treatments);

    void onDeviceStatus(String json);

    void onProfile(List<Profile> profiles);

    void onFailure(Throwable e);
}
