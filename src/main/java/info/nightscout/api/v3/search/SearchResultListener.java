package info.nightscout.api.v3.search;

import info.nightscout.api.v3.documents.Devicestatus;
import info.nightscout.api.v3.documents.Profile;
import info.nightscout.api.v3.documents.Treatment;

import java.util.List;

public interface SearchResultListener {

    void onTreatment(List<Treatment> results);

    void onDeviceStatus(List<Devicestatus> results);

    void onProfile(List<Profile> results);

    void onFailure(Throwable e);
}
