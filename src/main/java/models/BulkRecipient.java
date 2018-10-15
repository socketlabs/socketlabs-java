package models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Map;

public class BulkRecipient implements interfaces.BulkRecipient {
    private String email;
    private String friendlyName;
    private Map<String, String> mergeData;

    public BulkRecipient(String email) {
        this.email = email;
    }

    public BulkRecipient(String email, String friendlyName) {
        this.email = email;
        this.friendlyName = friendlyName;
    }

    public BulkRecipient(String email, String friendlyName, Map<String, String> mergeData) {
        this.email = email;
        this.friendlyName = friendlyName;
        this.mergeData = mergeData;
    }


    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public String getFriendlyName() {
        return this.friendlyName;
    }

    @Override
    public void setFriendlyName(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    @JsonIgnore
    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public Map<String, String> getMergeData() {
        return this.mergeData;
    }

    @Override
    public void setMergeData(Map<String, String> mergeData) {
        this.mergeData = mergeData;
    }
}
