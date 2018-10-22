package com.socketLabs.serialization;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

class Address {

    @JsonProperty("emailAddress")
    private String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String friendlyName;

    public Address(String email, String friendlyName) {
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

}
