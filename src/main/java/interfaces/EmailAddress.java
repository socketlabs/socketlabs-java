package interfaces;

public interface EmailAddress {

    String getEmail();
    void setEmail(String email);

    String getFriendlyName();
    void setFriendlyName(String friendlyName);

    boolean isValid();
    
}