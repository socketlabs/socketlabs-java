package interfaces;

import models.EmailAddress;

import java.util.HashMap;
import java.util.Map;

public interface BulkRecipient {
    EmailAddress getEmailAddress();
    void setEmailAddress(EmailAddress emailAddress);


    HashMap<String, String> getMergeData();
    void setMergeData(HashMap<String, String> mergeData);

    boolean isValid();

}
