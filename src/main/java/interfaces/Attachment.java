package interfaces;

import models.CustomHeader;

import java.util.List;

public interface Attachment {

    byte[] getContent();
    void setContent(byte[] content);

    String getContentId();
    void setContentId(String contentId);

    String getMimeType();
    void setMimeType(String mimeType);

    String getName();
    void setName(String name);

    List<CustomHeader> getCustomHeaders();

    void setCustomHeaders(List<CustomHeader> customHeaders);
    
}
