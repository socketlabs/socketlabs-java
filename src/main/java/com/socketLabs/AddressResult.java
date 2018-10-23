package com.socketLabs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressResult {

    @JsonProperty("EmailAddress")
    private String emailAddress;
    @JsonProperty("Accepted")
    private boolean accepted;
    @JsonProperty("ErrorCode")
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
