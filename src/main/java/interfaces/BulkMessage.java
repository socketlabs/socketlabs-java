package interfaces;

import models.BulkRecipient;
import models.MergeData;

import java.util.List;

public interface BulkMessage extends MessageBase {
    List<models.BulkRecipient> getTo();
    void setTo(List<BulkRecipient> to);
    MergeData getMergeData();
    void setMergeData(MergeData mergeData);
}
