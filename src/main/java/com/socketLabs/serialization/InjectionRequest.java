package com.socketLabs.serialization;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

class InjectionRequest {

    @JsonProperty("ServerId")
    private String serverId;

    @JsonProperty("ApiKey")
    private String apiKey;

    @JsonProperty("Messages")
    private List<Message> messages;

    public InjectionRequest(String serverId, String apiKey, List<Message> messages) {
        this.serverId = serverId;
        this.apiKey = apiKey;
        this.messages = messages;
    }

    public String getServerId() {
        return serverId;
    }
    public void setServerId(String serverId) {
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
