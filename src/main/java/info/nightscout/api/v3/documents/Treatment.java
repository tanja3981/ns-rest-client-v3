package info.nightscout.api.v3.documents;

public class Treatment extends DocumentBase implements Document {
    String eventType;
    String created_at;
    String glucose;
    String glucoseType;
    String units;
    Number carbs;
    Number insulin;
    String notes;
    String enteredBy;

}
