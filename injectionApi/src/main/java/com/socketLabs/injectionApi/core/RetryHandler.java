package com.socketLabs.injectionApi.core;

import com.socketLabs.injectionApi.RetrySettings;
import com.socketLabs.injectionApi.SendResponse;
import com.socketLabs.injectionApi.SendResult;


import javax.net.ssl.SSLEngineResult;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RetryHandler {

    private HttpRequest httpRequest;
    private String endPointUrl;
    private RetrySettings retrySettings;

    private Set<Integer> ErrorStatusCodes = new HashSet<>(Arrays.asList(500, 502, 503, 504));

    /// <summary>
    /// Creates a new instance of the <c>RetryHandler</c>.
    /// </summary>
    /// <param name="request">A <c>HttpRequest</c> instance</param>
    /// <param name="endpointUrl">The SocketLabs Injection API endpoint Url</param>
    /// <param name="settings">A <c>RetrySettings</c> instance</param>
    public RetryHandler(HttpRequest request, String endpointUrl, RetrySettings settings){
        httpRequest = request;
        endPointUrl = endpointUrl;
        retrySettings = settings;
    }

    public SendResponse Send() throws Exception {
        if (retrySettings.getMaximumNumberOfRetries()== 0){
            return httpRequest.SendRequest();
        }
        int attempts = 0;
        do {
            Duration waitInterval = retrySettings.getNextWaitInterval(attempts);

            try{
                SendResponse response = httpRequest.SendRequest();
//                response.networkResponse().code()
//                if (ErrorStatusCodes.contains(response.networkRes))
//                    throw new IOException();
                return response;
            }
//            catch timeout exception
            catch (IOException e)
            {
                attempts++;
                if (attempts > retrySettings.getMaximumNumberOfRetries()) throw new Exception();
                TimeUnit.MILLISECONDS.sleep(waitInterval.toMillis());
            }
        } while (true);

    }

}
