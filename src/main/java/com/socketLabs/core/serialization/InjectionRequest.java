package com.socketLabs.core.serialization;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

class InjectionRequest {

    @JsonProperty("ServerId")
    private int serverId;

    @JsonProperty("ApiKey")
    private String apiKey;

    @JsonProperty("Messages")
    private List<Message> messages;

    public InjectionRequest(int serverId, String apiKey, List<Message> messages) {
        this.serverId = serverId;
        this.apiKey = apiKey;
        this.messages = messages;
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

    public List<Message> getMessages() {
        return messages;
    }
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }



}
