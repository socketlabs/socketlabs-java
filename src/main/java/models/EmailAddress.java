package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EmailAddress implements interfaces.EmailAddress {

    @JsonProperty("emailAddress")
    private String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String friendlyName;

    public EmailAddress(String email) {
        this.email = email;
    }

    public EmailAddress(String email, String friendlyName) {
        this.email = email;
        this.friendlyName = friendlyName;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public String toString() {
        return String.format("%s <%s>", this.friendlyName, this.email);
    }
}
