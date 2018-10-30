package com.socketLabs.injectionApi.message;

/**
 * Represents an individual email address for a message
 * 
 * Example:
 * <code>
 *     EmailAddress emailAddress = new EmailAddress("recipient@example.com", "Recipient 1");
 * </code>
 */
public class EmailAddress {

    /**
     * A valid email address
     */
    private String emailAddress;

    /**
     * The friendly or display name for the recipient.
     */
    private String friendlyName;

    /**
     * Creates a new instance of the email address class and sets the email address and the friendly name.
     * @param emailAddress String
     */
    public EmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Creates a new instance of the email address class and sets the email address and the friendly name.
     * @param emailAddress String
     * @param friendlyName String
     */
    public EmailAddress(String emailAddress, String friendlyName) {
        this.emailAddress = emailAddress;
        this.friendlyName = friendlyName;
    }

    /**
     * Gets the email address for the recipient
     * @return String
     */
    public String getEmailAddress() {
        return this.emailAddress;
    }

    /**
     * Sets the email address for the recipient
     * @param value String
     */
    public void setEmailAddress(String value) {
        this.emailAddress = value;
    }

    /**
     * Gets the friendly name for the recipient
     * @return String
     */
    public String getFriendlyName() {
        return this.friendlyName;
    }

    /**
     * Sets the friendly name for the Recipient
     * @param value String
     */
    public void setFriendlyName(String value) {
        this.friendlyName = value;
    }

    /**
     * Determines if the email address is valid
     * @return boolean - true if valid, false if not
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
