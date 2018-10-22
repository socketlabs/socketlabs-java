package com.socketLabs.serialization;

import com.fasterxml.jackson.annotation.JsonProperty;

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
    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}
