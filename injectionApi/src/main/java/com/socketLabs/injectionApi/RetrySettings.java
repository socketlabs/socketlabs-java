package com.socketLabs.injectionApi;

import jdk.jfr.Timespan;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

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

    /// <summary>
    /// Get the time period to wait before next call
    /// </summary>
    /// <param name="numberOfAttempts"></param>
    /// <returns></returns>
    public Duration getNextWaitInterval(int numberOfAttempts){

        long interval = Math.min(
                minimumRetryTIme.toMillis() + getRetryDelta(numberOfAttempts),
                maximumRetryTime.toMillis());
        return Duration.ofMillis(interval);

    }

    public int getRetryDelta(int numberOfAttempts){

        Random random = new Random();

        int min = (int) (Duration.ofSeconds(1).toMillis() * 0.8);
        int max = (int) (Duration.ofSeconds(1).toMillis() * 1.2);
        int randomNumber = random.nextInt(max - min) + min;

        return (int) ((Math.pow(2.0, numberOfAttempts) - 1.0) * randomNumber);

    }

}
