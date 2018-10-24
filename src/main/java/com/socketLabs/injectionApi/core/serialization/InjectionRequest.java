package com.socketLabs.injectionApi.core.serialization;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

class InjectionRequest {

    @JsonProperty("ServerId")
    private int serverId;

    @JsonProperty("ApiKey")
    private String apiKey;

    @JsonProperty("Messages")
    private List<MessageJson> messageJsons;

    public InjectionRequest(int serverId, String apiKey, List<MessageJson> messageJsons) {
        this.serverId = serverId;
        this.apiKey = apiKey;
        this.messageJsons = messageJsons;
    }

    public int getServerId() {
        return serverId;
    }
    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    public String getApiKey() {
        return apiKey;
    }
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public List<MessageJson> getMessageJsons() {
        return messageJsons;
    }
    public void setMessageJsons(List<MessageJson> messageJsons) {
        this.messageJsons = messageJsons;
    }



}
