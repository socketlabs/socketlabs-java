package interfaces;

import models.EmailAddress;

import java.util.Map;

public interface BulkRecipient {
    EmailAddress getEmailAddress();
    void setEmailAddress(EmailAddress emailAddress);
    boolean isValid();
    Map<String, String> getMergeData();
    void setMergeData(Map<String, String> mergeData);
}
