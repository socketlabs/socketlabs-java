package com.socketLabs.serialization;

import com.fasterxml.jackson.annotation.JsonProperty;

class CustomHeader {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Value")
    private String value;

    public CustomHeader() {

    }

    public CustomHeader(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return this.value;
    }
    public void setValue(String value) {
        this.value = value;
    }

}
