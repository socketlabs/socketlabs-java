package interfaces;

import models.BulkRecipient;

import java.util.HashMap;
import java.util.List;

public interface BulkMessage extends MessageBase {

    List<interfaces.BulkRecipient> getTo();
    HashMap<String, String> getGlobalMergeData();
    void setGlobalMergeData(HashMap<String, String> globalMergeData);
    
}
