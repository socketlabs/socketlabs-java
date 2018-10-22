package com.socketLabs;

public class AddressResult {

    private String emailAddress;
    private boolean accepted;
    private String errorCode;

    public AddressResult(String emailAddress, boolean accepted, String errorCode) {
        this.emailAddress = emailAddress;
        this.accepted = accepted;
        this.errorCode = errorCode;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String value) {
        this.emailAddress = value;
    }

    public boolean isAccepted() {
        return accepted;
    }
    public void setAccepted(boolean value) {
        this.accepted = value;
    }

    public String getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(String value) {
        this.errorCode = value;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", this.errorCode, this.emailAddress);
    }
}
