package com.socketLabs.models;


public class EmailAddress {

    private String email;

    private String friendlyName;

    public EmailAddress(String email) {
        this.email = email;
    }

    public EmailAddress(String email, String friendlyName) {
        this.email = email;
        this.friendlyName = friendlyName;
    }

    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getFriendlyName() {
        return this.friendlyName;
    }
    public void setFriendlyName(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public boolean isValid() {
        if (this.email == null || this.email.isEmpty()) {
            return false;
        }

        if (!this.email.contains("@") || !this.email.contains(".")) {
            return false;
        }

        if (this.email.length() > 320) {
            return false;
        }

        String[] parts = this.email.split("@");

        if (parts[0].trim().length() < 1 || parts[1].trim().length() < 1) {
            return false;
        }

        return true;
    }

    /**
     *
     */
    @Override
    public String toString() {
        if (this.friendlyName == null || this.friendlyName.isEmpty()) {
            return this.email;
        }
        return String.format("%s <%s>", this.friendlyName, this.email);
    }
}
