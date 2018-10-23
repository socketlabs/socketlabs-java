package com.socketLabs.core.serialization;

import com.socketLabs.models.BasicMessage;
import com.socketLabs.models.BulkMessage;

public class InjectionRequestFactory{

    private int serverId;

    private String apiKey;

    public InjectionRequestFactory(int serverId, String apiKey) {
        this.serverId = serverId;
        this.apiKey = apiKey;
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

    public InjectionRequest GenerateRequest(BulkMessage message) {

        // TODO: Convert Bulk to Json
        return null;

    }

    public InjectionRequest GenerateRequest(BasicMessage message) {

        // TODO: Convert Basic to Json
        return null;
    }


    // TODO: Helper methods
}

