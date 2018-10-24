package com.socketLabs.injectionApi.core.serialization;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
class AttachmentJson {

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
    private List<CustomHeaderJson> customHeaderJsons;

/*
    public AttachmentJson() {
    }

    public AttachmentJson(String name, String mimeType, String contentId, byte[] content, List<CustomHeaderJson> customHeaderJsons) {
        this.name = name;
        this.mimeType = mimeType;
        this.contentId = contentId;
        this.content = content;
        this.customHeaderJsons = customHeaderJsons;
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

    public List<CustomHeaderJson> getCustomHeaderJsons() {
        return customHeaderJsons;
    }
    public void setCustomHeaderJsons(List<CustomHeaderJson> value) {
        this.customHeaderJsons = value;
    }

}
