package com.socketLabs.injectionApi.core.serialization;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Represents a merge field as a field and value pair.
 * To be serialized into JSON string before sending to the Injection Api.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
class MergeFieldJson {

    /** The field of your merge field. */
    @JsonProperty("Field")
    private String field;
    /** The value for your merge field. */
    @JsonProperty("Value")
    private String value;

    /**
     * Creates a new instance of the MergeFieldJson class and sets the field and value pair.
     * @param field The field of your merge field.
     * @param value The value for your merge field.
     */
    public MergeFieldJson(String field, String value) {
        this.field = field;
        this.value = value;
    }

    /**
     * Get the field of your merge field.
     * @return String
     */
    public String getField() {
        return field;
    }

    /**
     * Set the field of your merge field.
     * @param value String
     */
    public void setField(String value) {
        this.field = value;
    }

    /**
     * Get the merge field value.
     * @return String
     */
    public String getValue() {
        return value;
    }
    /**
     * Set the merge field value.
     * @param value String
     */
    public void setValue(String value) {
        this.value = value;
    }
}
