package com.socketLabs.injectionApi.message;

import java.util.HashMap;
import java.util.Map;

public class BulkRecipient  {

    private String emailAddress;
    private String friendlyName;
    private Map<String, String> mergeData = new HashMap<String, String>();

    public BulkRecipient(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public BulkRecipient(String emailAddress, String name) {
        this.emailAddress = emailAddress;
        this.friendlyName = name;
    }
    public BulkRecipient(String emailAddress, String name, Map<String, String> mergeData) {
        this.emailAddress = emailAddress;
        this.friendlyName = name;
        this.mergeData = mergeData;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }
    public void setEmailAddress(String value) {
        this.emailAddress = value;
    }

    public String getFriendlyName() {
        return this.friendlyName;
    }
    public void setFriendlyName(String value) {
        this.friendlyName = value;
    }

    public Map<String, String> getMergeData() {
        return this.mergeData;
    }
    public void setMergeData(Map<String, String> mergeData) {
        this.mergeData = mergeData;
    }
    public void addMergeData(String field, String value) {
        this.mergeData.put(field, value);
    }

    public boolean isValid() {

        if (this.emailAddress == null || this.emailAddress.isEmpty()) {
            return false;
        }
        if (!this.emailAddress.contains("@") || !this.emailAddress.contains(".")) {
            return false;
        }
        if (this.emailAddress.length() > 320) {
            return false;
        }

        String[] parts = this.emailAddress.split("@");
        if (parts[0].trim().length() < 1 || parts[1].trim().length() < 1) {
            return false;
        }

        char[] badEmailCharacters =  { ',', ' ', ';', (char)191 };
        for (char c: badEmailCharacters) {
            if (this.emailAddress.indexOf(c) >= 0)
                return false;
        }

        return true;
    }
    @Override
    public String toString() {
        if (this.friendlyName == null || this.friendlyName.isEmpty()) {
            return this.emailAddress;
        }
        return String.format("%s <%s>", this.friendlyName, this.emailAddress);
    }

}
