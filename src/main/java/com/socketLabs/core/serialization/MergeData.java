package com.socketLabs.core.serialization;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
class MergeData {

    @JsonProperty("PerMessage")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<List<MergeField>> perMessage;

    @JsonProperty("Global")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<MergeField> global;

    public MergeData(List<List<MergeField>> perMessage, List<MergeField> global) {
        this.perMessage = perMessage;
        this.global = global;
    }

    public List<List<MergeField>> getPerMessage() {
        return perMessage;
    }
    public void setPerMessage(List<List<MergeField>> value) {
        this.perMessage = value;
    }

    public List<MergeField> getGlobal() {
        return global;
    }
    public void setGlobal(List<MergeField> value) {
        this.global = value;
    }

}
