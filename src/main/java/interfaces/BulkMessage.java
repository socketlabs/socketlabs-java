package interfaces;

import models.BulkRecipient;

import java.util.List;
import java.util.Map;

public interface BulkMessage extends MessageBase {
    List<models.BulkRecipient> getTo();
    void setTo(List<BulkRecipient> to);
    Map<String, String> getGlobalMergeData();
    void setGlobalMergeData(Map<String, String> globalMergeData);
}
