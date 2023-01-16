package com.socketLabs.injectionApi.core.serialization;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a metadata item as a key and value pair.
 * To be serialized into JSON string before sending to the Injection Api.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
class MetadataJson {

    /** The key of your metadata */
    @JsonProperty("key")
    private String key;
    /** The value for your metadata */
    @JsonProperty("Value")
    private String value;

    /**
     * Creates a new instance of the MetadataJson class and sets the key and value pair.
     */
    public MetadataJson() {
    }

    /**
     * Creates a new instance of the MetadataJson class and sets the key and value pair.
     * @param key The key of your metadata
     * @param value The value for your metadata
     */
    public MetadataJson(String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Gets the key of the metadata.
     * @return String
     */
    public String getKey() {
        return this.key;
    }

    /**
     * Gets the key of the metadata.
     * @param value String
     */
    public void setKey(String value) {
        this.key = value;
    }

    /**
     *  Sets the value of the metadata.
     * @return String
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Gets the value of the metadata.
     * @param value String
     */
    public void setValue(String value) {
        this.value = value;
    }

}
