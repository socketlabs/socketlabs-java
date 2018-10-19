package interfaces;

import java.util.Map;

public interface BulkRecipient {

    String getEmail();

    String getFriendlyName();
    void setFriendlyName(String friendlyName);

    Map<String, String> getMergeData();
    void setMergeData(Map<String, String> mergeData);

    boolean isValid();

}
