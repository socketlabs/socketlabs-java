package com.socketLabs.injectionApi.core.models;

import com.socketLabs.injectionApi.core.*;

/**
 *  The object representing the Api Key Parse Result.
 */
public class ApiKeyParseResult {

    public ApiKeyParseResult(ApiKey apiKey, ApiKeyParseResultCode resultCode)
    {
        this.apiKey = apiKey;
        this.resultCode = resultCode;
    }
	
    private ApiKey apiKey;
    private ApiKeyParseResultCode resultCode;
    
    /**
     * Get the Api Key
     * @return
     */
    public ApiKey getApiKey() {
        return apiKey;
    }
    /**
     * Set the Api Key
     * @param value int
     */
    public void setApiKey(ApiKey value) {
        this.apiKey = value;
    }
    /**
     * Get the result code from the parse
     * @return
     */
    public ApiKeyParseResultCode getResultCode() {
        return resultCode;
    }
    /**
     * Set the result code from the parse
     * @param value int
     */
    public void setResultCode(ApiKeyParseResultCode value) {
        this.resultCode = value;
    }

}