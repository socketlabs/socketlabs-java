package models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MergeData {

    @JsonProperty("PerMessage")
    private ArrayList<HashMap<String, String>> perMessage;

    @JsonProperty("Global")
    private HashMap<String, String> global;

    public MergeData() {
        this.perMessage = new ArrayList<>();
        this.global = new HashMap<>();
    }

    public List<HashMap<String, String>> getPerMessage() {
        return perMessage;
    }

    public void setPerMessage(ArrayList<HashMap<String, String>> perMessage) {
        this.perMessage = perMessage;
    }

    public HashMap<String, String> getGlobal() {
        return global;
    }

    public void setGlobal(HashMap<String, String> global) {
        this.global = global;
    }

    public void addToPerMessage(HashMap<String, String> mergeData) {
        this.perMessage.add(mergeData);
    }
}
