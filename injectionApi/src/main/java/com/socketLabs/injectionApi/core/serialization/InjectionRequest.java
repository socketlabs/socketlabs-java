package com.socketLabs.injectionApi.core.serialization;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a injection request for sending to the Injection Api.
 */
class InjectionRequest {

    /** Your SocketLabs ServerId number */
    @JsonProperty("ServerId")
    private String serverId;
    /** Your SocketLabs Injection API key */
    @JsonProperty("ApiKey")
    private String apiKey;
    /** The list of messages to be sent */
    @JsonProperty("Messages")
    private List<MessageJson> messageJsons;

    /**
     * Creates a new instance of the InjectionRequest class
     * @param serverId Your SocketLabs ServerId number
     * @param apiKey Your SocketLabs Injection API key
     * @param messageJsons The messages included in the Injection Request
     */
    public InjectionRequest(int serverId, String apiKey, List<MessageJson> messageJsons) {
        this.serverId = Integer.toString(serverId);
        this.apiKey = apiKey;
        this.messageJsons = messageJsons;
    }

    /**
     * Gets the server ID for the Injection Request.
     * @return String
     */
    public String getServerId() {
        return serverId;
    }

    /**
     * Sets the server ID for the injection Request.
     * @param serverId String
     */
    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    /**
     * Gets the SocketLabs Injection API key for the Injection Request.
     * @return String
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * Sets the SocketLabs Injection API key for the Injection Request.
     * @param apiKey String
     */
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Gets the list of messages to be sent.
     * @return String
     */
    public List<MessageJson> getMessageJsons() {
        return messageJsons;
    }

    /**
     * Sets or sets the list of messages to be sent.
     * @param messageJsons
     */
    public void setMessageJsons(List<MessageJson> messageJsons) {
        this.messageJsons = messageJsons;
    }



}
