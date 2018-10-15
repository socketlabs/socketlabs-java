package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import interfaces.Customheader;

public class CustomHeader implements Customheader {

    private String name;
    private String value;

    public CustomHeader() {

    }

    public CustomHeader(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @JsonIgnore
    @Override
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
