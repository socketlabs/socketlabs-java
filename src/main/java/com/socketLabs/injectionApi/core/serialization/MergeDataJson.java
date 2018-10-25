package com.socketLabs.injectionApi.core.serialization;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
class MergeDataJson {

    @JsonProperty("PerMessage")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<List<MergeFieldJson>> perMessage;

    @JsonProperty("Global")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<MergeFieldJson> global;

    public MergeDataJson(List<List<MergeFieldJson>> perMessage, List<MergeFieldJson> global) {
        this.perMessage = perMessage;
        this.global = global;
    }

    public List<List<MergeFieldJson>> getPerMessage() {
        return perMessage;
    }
    public void setPerMessage(List<List<MergeFieldJson>> value) {
        this.perMessage = value;
    }

    public List<MergeFieldJson> getGlobal() {
        return global;
    }
    public void setGlobal(List<MergeFieldJson> value) {
        this.global = value;
    }

}
