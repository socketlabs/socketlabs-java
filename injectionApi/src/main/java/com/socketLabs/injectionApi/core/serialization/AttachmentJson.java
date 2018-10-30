package com.socketLabs.injectionApi.core.serialization;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.List;

/**
 * Represents a message attachment in the form of a byte array.
 * To be serialized into JSON string before sending to the Injection Api.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
class AttachmentJson {

    /** Name of attachment (displayed in email clients) */
    @JsonProperty("Name")
    private String name;
    /** The ContentType (MIME type) of the attachment.
     * Example - text/plain, image/jpeg */
    @JsonProperty("ContentType")
    private String mimeType;
    /** When set, used to embed an image within the body of an email message. */
    @JsonProperty("ContentId")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String contentId;
    /** The BASE64 encoded string containing the contents of an attachment. */
    @JsonProperty("Content")
    private byte[] content;
    /** A list of custom headers added to the attachment. */
    @JsonProperty("CustomHeaders")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<CustomHeaderJson> customHeaderJsons;

    /**
     * Gets the name of the Attachment
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the Attachment
     * @param value String
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the MIME type of the Attachment
     * @return String
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * Sets the MIME type of the Attachment
     * @param value String
     */
    public void setMimeType(String value) {
        this.mimeType = value;
    }

    /**
     * Gets the content ID of the Attachment
     * @return String
     */
    public String getContentId() {
        return contentId;
    }

    /**
     * Sets the content ID of the Attachment
     * @param value String
     */
    public void setContentId(String value) {
        this.contentId = value;
    }

    /**
     * Gets the content of the Attachment
     * @return byte[]
     */
    public byte[] getContent() {
        return content;
    }

    /**
     * Sets the content of the Attachment
     * @param value byte[]
     */
    public void setContent(byte[] value) {
        this.content = value;
    }

    /**
     *  Gets the list of Custom Headers for the Attachment
     * @return List<CustomHeaderJson>
     */
    public List<CustomHeaderJson> getCustomHeaderJsons() {
        return customHeaderJsons;
    }

    /**
     * Sets the list of Custom Headers for the Attachment
     * @param value List<CustomHeaderJson>
     */
    public void setCustomHeaderJsons(List<CustomHeaderJson> value) {
        this.customHeaderJsons = value;
    }

}
