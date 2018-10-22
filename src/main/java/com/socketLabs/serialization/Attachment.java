package com.socketLabs.serialization;

import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.List;

class Attachment {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("ContentType")
    private String mimeType;

    @JsonProperty("ContentId")
    private String contentId;

    @JsonProperty("Content")
    private byte[] content;

    @JsonProperty("CustomHeaders")
    private List<CustomHeader> customHeaders;

/*
    public Attachment() {
    }

    public Attachment(String name, String mimeType, String contentId, byte[] content, List<CustomHeader> customHeaders) {
        this.name = name;
        this.mimeType = mimeType;
        this.contentId = contentId;
        this.content = content;
        this.customHeaders = customHeaders;
    }
*/

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getMimeType() {
        return mimeType;
    }
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getContentId() {
        return contentId;
    }
    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public byte[] getContent() {
        return content;
    }
    public void setContent(byte[] content) {
        this.content = content;
    }

    public List<CustomHeader> getCustomHeaders() {
        return customHeaders;
    }
    public void setCustomHeaders(List<CustomHeader> customHeaders) {
        this.customHeaders = customHeaders;
    }

}
