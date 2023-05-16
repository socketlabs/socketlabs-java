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

            if (wholeApiKey.length() != 61)
                return ApiKeyParseResult.InvalidKeyLength;

            if (wholeApiKey.indexOf('.') == -1)
                return ApiKeyParseResult.InvalidKeyFormat;

            //extract public part
            int publicPartEnd = wholeApiKey.substring(0, 50).indexOf('.'); //don't check more than 50 chars
            if (publicPartEnd == -1)
                return ApiKeyParseResult.InvalidUnableToExtractPublicPart;

            String publicPart = wholeApiKey.substring(0, publicPartEnd);
            if (publicPart.length() != 20)
                return ApiKeyParseResult.InvalidPublicPartLength;

            //now extract the private part
            if (wholeApiKey.length() <= publicPartEnd + 1)
                return ApiKeyParseResult.InvalidUnableToExtractSecretPart;

            String privatePart = wholeApiKey.substring(publicPartEnd + 1);
            if (privatePart.length() != 40)
                return ApiKeyParseResult.InvalidSecretPartLength;

            //success
            return ApiKeyParseResult.Success;
        }

}