package com.socketLabs.models;


public class CustomHeader {

    private String name;

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

    public boolean isValid() {
        if (this.name == null || this.name.isEmpty()) {
            return false;
        }

        if (this.value == null || this.value.isEmpty()) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "CustomHeader{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
