package models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;

public class BulkRecipient implements interfaces.BulkRecipient {

    private EmailAddress emailAddress;

    @JsonIgnore()
    private HashMap<String, String> mergeData;

    public BulkRecipient(EmailAddress emailAddress) {
        this.emailAddress = emailAddress;
    }

    public BulkRecipient(EmailAddress emailAddress, HashMap<String, String> mergeData) {
        this.emailAddress = emailAddress;
        this.mergeData = mergeData;
    }

    @Override
    public EmailAddress getEmailAddress() {
        return this.emailAddress;
    }

    @Override
    public void setEmailAddress(EmailAddress emailAddress) {
        this.emailAddress = emailAddress;
    }

    @JsonIgnore
    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public HashMap<String, String> getMergeData() {
        return this.mergeData;
    }

    @Override
    public void setMergeData(HashMap<String, String> mergeData) {
        this.mergeData = mergeData;
    }
}
