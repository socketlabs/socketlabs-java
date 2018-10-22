package com.socketLabs.models;


public class EmailAddress {

    private String emailAddress;
    private String friendlyName;

    public EmailAddress(String email) {
        this.emailAddress = email;
    }
    public EmailAddress(String email, String friendlyName) {
        this.emailAddress = email;
        this.friendlyName = friendlyName;
    }

    public String getEmail() {
        return this.emailAddress;
    }
    public void setEmail(String value) {
        this.emailAddress = value;
    }

    public String getFriendlyName() {
        return this.friendlyName;
    }
    public void setFriendlyName(String value) {
        this.friendlyName = value;
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
            if (this.emailAddress.indexOf(c) <= -1)
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
