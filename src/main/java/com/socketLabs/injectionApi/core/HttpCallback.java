package com.socketLabs.injectionApi.core;

import com.socketLabs.injectionApi.SendResponse;

/**
 * An interface describing a callback mechanism for the
 * asynchronous, rate limit aware API connection.
 */
public interface HttpCallback {
    /**
     * Callback method in case of an error.
     * @param ex the error that was thrown.
     */
    public void onError(Exception ex);

    /**
     * Callback method in case of a valid HttpResponse.
     * @param response the valid HttpResponse.
     */
    public void onResponse(SendResponse response);

}
