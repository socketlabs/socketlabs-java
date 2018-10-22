package com.socketLabs.serialization;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
class MergeField {

    @JsonProperty("Field")
    private String field;

    @JsonProperty("Value")
    private String value;

    public MergeField(String field, String value) {
        this.field = field;
        this.value = value;
    }

    public String getField() {
        return field;
    }
    public void setField(String value) {
        this.field = value;
    }

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}
