package com.socketLabs.injectionApi.core.serialization;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents MergeData for a single message.
 * To be serialized into JSON string before sending to the Injection Api.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
class MergeDataJson {

    /** Defines merge field data for each message. */
    @JsonProperty("PerMessage")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<List<MergeFieldJson>> perMessage;

    /** Defines merge field data for all messages in the request. */
    @JsonProperty("Global")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<MergeFieldJson> global;

    /**
     * Creates a new instance of the MergeDataJson class.
     */
    public MergeDataJson() {
    }

    /**
     * Creates a new instance of the MergeDataJson class.
     * @param perMessage merge field data for each message.
     * @param global merge field data for all messages in the request.
     */
    public MergeDataJson(List<List<MergeFieldJson>> perMessage, List<MergeFieldJson> global) {
        this.perMessage = perMessage;
        this.global = global;
    }

    /**
     * Set merge field data for each message.
     * @return List<List<MergeFieldJson>>
     */
    public List<List<MergeFieldJson>> getPerMessage() {
        return perMessage;
    }
    /**
     * Set merge field data for each message.
     * @param value List<List<MergeFieldJson>>
     */
    public void setPerMessage(List<List<MergeFieldJson>> value) {
        this.perMessage = value;
    }

    /**
     * Get merge field data for all messages in the request
     * @return List<MergeFieldJson>
     */
    public List<MergeFieldJson> getGlobal() {
        return global;
    }
    /**
     * Set merge field data for all messages in the request
     * @param value List<MergeFieldJson>
     */
    public void setGlobal(List<MergeFieldJson> value) {
        this.global = value;
    }

}
