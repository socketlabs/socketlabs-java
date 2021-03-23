package com.socketLabs.injectionApi;

import java.time.Duration;

public class RetrySettings {
    private final int defaultNumberOfRetries = 0;
    private final int maximumAllowedNumberOfRetries = 5;
    private Duration minimumRetryTIme = Duration.ofSeconds(1);
    private Duration maximumRetryTime = Duration.ofSeconds(10);
    private int maximumNumberOfRetries = defaultNumberOfRetries;

    /// <summary>
    ///  Creates a new instance of the <c>RetrySettings</c>.
    /// </summary>
    /// <param name="maximumRetries"></param>
    public RetrySettings(int maximumRetries){
        if (maximumRetries < 0)
            throw new IllegalArgumentException("maximumNumberOfRetries must be greater than 0");
        if (maximumRetries > 5)
            throw new IllegalArgumentException("The maximum number of allowed retries is " + maximumAllowedNumberOfRetries);
        maximumNumberOfRetries = maximumRetries;
    }

    /// <summary>
    /// The maximum number of retries when sending an Injection API Request before throwing an exception. Default: 0, no retries, you must explicitly enable retry settings
    /// </summary>
    public int getMaximumNumberOfRetries(){
        return maximumNumberOfRetries;
    }

    

}
