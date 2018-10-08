package com.socketLabs.injectionApi.core.serialization;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a custom header as a name and value pair.
 * To be serialized into JSON string before sending to the Injection Api.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
class CustomHeaderJson {

    /** The name of your custom header */
    @JsonProperty("Name")
    private String name;
    /** The value for your custom header */
    @JsonProperty("Value")
    private String value;

    /**
     * Creates a new instance of the CustomHeaderJson class and sets the name and value pair.
     */
    public CustomHeaderJson() {
    }

    /**
     * Creates a new instance of the CustomHeaderJson class and sets the name and value pair.
     * @param name The name of your custom header
     * @param value The value for your custom header
     */
    public CustomHeaderJson(String name, String value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Gets the name of the Custom Header.
     * @return String
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the name of the Custom Header.
     * @param value String
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     *  Sets the value of the Custom Header.
     * @return String
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Gets the value of the Custom Header.
     * @param value String
     */
    public void setValue(String value) {
        this.value = value;
    }

}
