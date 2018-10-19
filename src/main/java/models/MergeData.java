package models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

public class MergeData {

    @JsonProperty("PerMessage")
    private HashMap<String, String> perMessage;

    @JsonProperty("Global")
    private HashMap<String, String> global;

    public HashMap<String, String> getPerMessage() {
        return perMessage;
    }

    public void setPerMessage(HashMap<String, String> perMessage) {
        this.perMessage = perMessage;
    }

    public HashMap<String, String> getGlobal() {
        return global;
    }

    public void setGlobal(HashMap<String, String> global) {
        this.global = global;
    }
}
