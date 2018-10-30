package com.socketLabs.injectionApi;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The result of a single recipient in the Injection request.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressResult {

    /**
     * The recipient's email address.
     */
    @JsonProperty("EmailAddress")
    private String emailAddress;

    /**
     * Whether the recipient was accepted for delivery.
     */
    @JsonProperty("Accepted")
    private boolean accepted;

    /**
     * An error code detailing why the recipient was not accepted.
     */
    @JsonProperty("ErrorCode")
    private String errorCode;

    /**
     * Initializes a new instance of the SendResponse class
     * @param emailAddress the recipient's email address.
     * @param accepted whether the recipient was accepted for delivery.
     * @param errorCode an error code detailing why the recipient was not accepted.
     */
    public AddressResult(String emailAddress, boolean accepted, String errorCode) {
        this.emailAddress = emailAddress;
        this.accepted = accepted;
        this.errorCode = errorCode;
    }

    /**
     * Get the recipient's email address.
     * @return String
     */
    public String getEmailAddress() {
        return emailAddress;
    }
    /**
     * Set the recipient's email address.
     * @param value String
     */
    public void setEmailAddress(String value) {
        this.emailAddress = value;
    }

    /**
     * Get whether the recipient was accepted for delivery.
     * @return boolean
     */
    public boolean isAccepted() {
        return accepted;
    }
    /**
     * Set whether the recipient was accepted for delivery.
     * @param value boolean
     */
    public void setAccepted(boolean value) {
        this.accepted = value;
    }

    /**
     * Get an error code detailing why the recipient was not accepted.
     * @return String
     */
    public String getErrorCode() {
        return errorCode;
    }
    /**
     * Set an error code detailing why the recipient was not accepted.
     * @param value String
     */
    public void setErrorCode(String value) {
        this.errorCode = value;
    }

    /**
     * Represents the AddressResult class as a string.  Useful for debugging.
     * @return String
     */
    @Override
    public String toString() {
        return String.format("%s: %s", this.errorCode, this.emailAddress);
    }
}
