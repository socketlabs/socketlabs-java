package com.socketLabs.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;

public class BulkRecipient  {

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

    public EmailAddress getEmailAddress() {
        return this.emailAddress;
    }
    public void setEmailAddress(EmailAddress emailAddress) {
        this.emailAddress = emailAddress;
    }

    @JsonIgnore
    public boolean isValid() {
        return true;
    }

    public HashMap<String, String> getMergeData() {
        return this.mergeData;
    }

    
    public void setMergeData(HashMap<String, String> mergeData) {
        this.mergeData = mergeData;
    }
}
