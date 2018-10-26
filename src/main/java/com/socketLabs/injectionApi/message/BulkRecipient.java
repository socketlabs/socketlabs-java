package com.socketLabs.injectionApi.message;

import java.util.TreeMap;

public class BulkRecipient  {

    /**
     * A valid email address
     */
    private String emailAddress;
    /**
     * The friendly or display name for the recipient.
     */
    private String friendlyName;
    /**
     * A map containing Merge Data items unique to the recipient.
     */
    private TreeMap<String, String> mergeData  = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    /**
     * Creates a new instance of the BulkRecipient class and sets the email address.
     * @param emailAddress The email address for your bulk recipient.
     */
    public BulkRecipient(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Creates a new instance of the BulkRecipient class and sets the email address and friendly name.
     * @param emailAddress The email address for your bulk recipient.
     * @param friendlyName The friendly name for your bulk recipient.
     */
    public BulkRecipient(String emailAddress, String friendlyName) {
        this.emailAddress = emailAddress;
        this.friendlyName = friendlyName;
    }

    /**
     * Creates a new instance of the BulkRecipient class and sets the email address and merge data properties..
     * @param emailAddress The email address for your bulk recipient.
     * @param mergeData Merge data unique to the instance of the bulk recipient.
     */
    public BulkRecipient(String emailAddress, TreeMap<String, String> mergeData) {
        this.emailAddress = emailAddress;
        this.mergeData = mergeData;
    }
    /**
     * Creates a new instance of the BulkRecipient class and sets the email address, friendly name and merge data properties.
     * @param emailAddress The email address for your bulk recipient.
     * @param friendlyName The friendly name for your bulk recipient.
     * @param mergeData Merge data unique to the instance of the bulk recipient.
     */
    public BulkRecipient(String emailAddress, String friendlyName, TreeMap<String, String> mergeData) {
        this.emailAddress = emailAddress;
        this.friendlyName = friendlyName;
        this.mergeData = mergeData;
    }

    /**
     * Get the valid email address
     * @return String
     */
    public String getEmailAddress() {
        return this.emailAddress;
    }
    /**
     * Set the valid email address
     * @param value String
     */
    public void setEmailAddress(String value) {
        this.emailAddress = value;
    }

    /**
     * Get the friendly or display name for the recipient.
     * @return String
     */
    public String getFriendlyName() {
        return this.friendlyName;
    }
    /**
     * Set the friendly or display name for the recipient.
     * @param value String
     */
    public void setFriendlyName(String value) {
        this.friendlyName = value;
    }

    /**
     * Get the map containing Merge Data items unique to the recipient.
     * @return TreeMap<String, String>
     */
    public TreeMap<String, String> getMergeData() {
        return this.mergeData;
    }
    /**
     * Set the map containing Merge Data items unique to the recipient.
     * @param value TreeMap<String, String>
     */
    public void setMergeData(TreeMap<String, String> value) {
        this.mergeData = value;
    }
    /**
     * Adds a merge data entry to the BulkRecipient
     * @param field String
     * @param value String
     */
    public void addMergeData(String field, String value) {
        this.mergeData.put(field, value);
    }

    /**
     * Determines if the Email Address is valid.
     * <p>
     *     Does simple syntax validation on the address.
     * </p>
     * @return boolean
     */
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

    /**
     * Represents the email address as a string similar to how it would look in an email client.  Useful for debugging.
     * @return String
     */
    @Override
    public String toString() {
        if (this.friendlyName == null || this.friendlyName.isEmpty()) {
            return this.emailAddress;
        }
        return String.format("%s <%s>", this.friendlyName, this.emailAddress);
    }

}
