package com.socketLabs.injectionApi.core.serialization;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an individual email address for a message.
 * To be serialized into JSON string before sending to the Injection Api.
 */
class AddressJson {

    /**
     * A valid email address
     */
    @JsonProperty("emailAddress")
    private String email;

    /**
     * The friendly or display name for the recipient.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("friendlyName")
    private String friendlyName;

    /**
     * Creates a new instance of the AddressJson class and sets the email address.
     * @param email A valid email address
     * @param friendlyName The friendly or display name for the recipient.
     */
    public AddressJson(String email, String friendlyName) {
        this.email = email;
        this.friendlyName = friendlyName;
    }

    /**
     * Gets the email for the recipient
     * @return String
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Sets the email for the recipient
     * @param value String
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the friendly name for the recipient
     * @return String
     */
    public String getFriendlyName() {
        return this.friendlyName;
    }

    /**
     * Sets the friendly name for the recipient
     * @param value String
     */
    public void setFriendlyName(String value) {
        this.friendlyName = value;
    }

}
