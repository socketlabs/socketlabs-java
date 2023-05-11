package com.socketLabs.injectionApi.core;

import com.socketLabs.injectionApi.core.*;

/**
 * SocketLabsClient is a wrapper for the SocketLabs Injection API that makes it easy to send messages and parse responses.
 */
public class ApiKeyParser {

        public ApiKeyParseResult Parse(String wholeApiKey)
        {
            var key = new ApiKey();
            key.setIsValidFormat(false);

            if (wholeApiKey == null || wholeApiKey.length() == 0)
                return new ApiKeyParseResult(null, ApiKeyParseResultCode.InvalidEmptyOrWhitespace);


            //extract public part
            var maxCount = Math.min(50, wholeApiKey.length());
            var publicPartEnd = wholeApiKey.substring(0, 50).indexOf('.', 0); //don't check more than 50 chars
            if (publicPartEnd == -1)
                return new ApiKeyParseResult(null, ApiKeyParseResultCode.InvalidUnableToExtractPublicPart);

            var publicPart = wholeApiKey.substring(0, publicPartEnd);


            //now extract the private part
            if (wholeApiKey.length() <= publicPartEnd + 1)
                return new ApiKeyParseResult(null, ApiKeyParseResultCode.InvalidUnableToExtractSecretPart);

            var privatePart = wholeApiKey.substring(publicPartEnd + 1);

            var ApiKey = new ApiKey();
            ApiKey.setPublicPart(publicPart);
            ApiKey.setPrivatePart(privatePart);
            ApiKey.setIsValidFormat(true);
            //success
            return new ApiKeyParseResult(ApiKey, ApiKeyParseResultCode.Success);
        }

}