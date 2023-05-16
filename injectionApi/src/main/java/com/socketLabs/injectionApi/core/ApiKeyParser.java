package com.socketLabs.injectionApi.core;

/**
 * Contains the method for parsing the Api Key.
 */
public class ApiKeyParser {
    
        /**
         * Parse the API key to determine what kind of key was provided.
         * @param wholeApiKey String
         * @return A ApiKeyParseResult with the parsing results
         */
        public ApiKeyParseResult Parse(String wholeApiKey)
        {
            if (wholeApiKey == null || wholeApiKey.length() == 0)
                return ApiKeyParseResult.InvalidEmptyOrWhitespace;

            //extract public part
            int publicPartEnd = wholeApiKey.substring(0, 50).indexOf('.'); //don't check more than 50 chars
            if (publicPartEnd == -1)
                return ApiKeyParseResult.InvalidUnableToExtractPublicPart;

            //now extract the private part
            if (wholeApiKey.length() <= publicPartEnd + 1)
                return ApiKeyParseResult.InvalidUnableToExtractSecretPart;

            //success
            return ApiKeyParseResult.Success;
        }

}