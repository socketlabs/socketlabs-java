package interfaces;

import models.EmailAddress;

import java.util.HashMap;
import java.util.Map;

public interface BulkRecipient {
    EmailAddress getEmailAddress();
    void setEmailAddress(EmailAddress emailAddress);
    boolean isValid();
    HashMap<String, String> getMergeData();
    void setMergeData(HashMap<String, String> mergeData);
}
