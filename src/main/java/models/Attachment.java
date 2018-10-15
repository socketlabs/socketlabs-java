package models;


import java.util.List;

public class Attachment implements interfaces.Attachment {

    private String name;
    private String mimeType;
    private String contentId;
    private byte[] content;
    private List<CustomHeader> customheadersList;

    public Attachment() {
    }

    @Override
    public byte[] getContent() {
        return this.content;
    }

    @Override
    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public String getContentId() {
        return this.contentId;
    }

    @Override
    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    @Override
    public String getMimeType() {
        return this.mimeType;
    }

    @Override
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<CustomHeader> getCustomHeaders() {
        return this.customheadersList;
    }

    @Override
    public void setCustomHeaders(List<CustomHeader> customHeaders) {
        this.customheadersList = customHeaders;
    }
}
