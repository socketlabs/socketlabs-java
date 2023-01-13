package com.socketLabs.injectionApi.core.serialization;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a metadata item as a name and value pair.
 * To be serialized into JSON string before sending to the Injection Api.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
class MetadataJson {

    /** The name of your metadata */
    @JsonProperty("Name")
    private String name;
    /** The value for your metadata */
    @JsonProperty("Value")
    private String value;

    /**
     * Creates a new instance of the MetadataJson class and sets the name and value pair.
     */
    public MetadataJson() {
    }

    /**
     * Creates a new instance of the MetadataJson class and sets the name and value pair.
     * @param name The name of your metadata
     * @param value The value for your metadata
     */
    public MetadataJson(String name, String value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Gets the name of the metadata.
     * @return String
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the name of the metadata.
     * @param value String
     */
    public void setName(String value) {
        this.name = value;
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
