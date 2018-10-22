package com.socketLabs.serialization;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
class Attachment {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("ContentType")
    private String mimeType;

    @JsonProperty("ContentId")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String contentId;

    @JsonProperty("Content")
    private byte[] content;

    @JsonProperty("CustomHeaders")
    @JsonInclude(JsonInclude.Include.NON_NULL)
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
    public void setName(String value) {
        this.name = value;
    }

    public String getMimeType() {
        return mimeType;
    }
    public void setMimeType(String value) {
        this.mimeType = value;
    }

    public String getContentId() {
        return contentId;
    }
    public void setContentId(String value) {
        this.contentId = value;
    }

    public byte[] getContent() {
        return content;
    }
    public void setContent(byte[] value) {
        this.content = value;
    }

    public List<CustomHeader> getCustomHeaders() {
        return customHeaders;
    }
    public void setCustomHeaders(List<CustomHeader> value) {
        this.customHeaders = value;
    }

}
