package client;

import com.fasterxml.jackson.annotation.JsonProperty;
import interfaces.MessageBase;

import java.util.List;

public class InjectionRequest {
    private String serverId;
    @JsonProperty("APIKey") private String apiKey;
    @JsonProperty("Messages") private List<MessageBase> messages;

    public InjectionRequest(String serverId, String apiKey, List<MessageBase> messages) {
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

    public List<MessageBase> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageBase> messages) {
        this.messages = messages;
    }
}
