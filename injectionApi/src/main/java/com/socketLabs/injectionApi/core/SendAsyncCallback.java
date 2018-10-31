package com.socketLabs.injectionApi.core;

import com.socketLabs.injectionApi.SendResponse;

import java.io.IOException;

/**
 * Represents the callback mechanism for the asynchronous HTTP Request
 */
public interface SendAsyncCallback {

    /**
     * Callback method in case of an error.
     * @param ex the error that was thrown.
     */
    public void onError(Exception ex);

    /**
     * Callback method in case of a valid SendResponse.
     * @param response the valid SendResponse.
     * @throws IOException IOException
     */
    public void onResponse(SendResponse response) throws IOException;

}
