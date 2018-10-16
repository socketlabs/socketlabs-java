package client;

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

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", this.errorCode, this.emailAddress);
    }
}
