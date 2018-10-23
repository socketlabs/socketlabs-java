package com.socketLabs.core.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.socketLabs.SendResponse;
import com.socketLabs.SendResult;
import com.socketLabs.core.HttpResponse;

import java.io.IOException;

public class InjectionResponseParser {

    /**
     * Parse the response from theInjection Api into SendResponse
     * @param response The response from the Injection Api request
     * @return A SendResponse from the Injection Api response
     * @throws IOException in case of a network error.
     */
    public SendResponse Parse(HttpResponse response) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        InjectionResponseDto injectionResponse = mapper.readValue(response.getContent(), InjectionResponseDto.class);

        SendResult resultEnum = DetermineSendResult(injectionResponse, response.getResponseCode());
        SendResponse newResponse = new SendResponse(resultEnum);
        newResponse.setTransactionReceipt(injectionResponse.getTransactionReceipt());

        if (resultEnum == SendResult.Warning && (injectionResponse.getMessageResults() != null && injectionResponse.getMessageResults().length > 0))
        {
            SendResult r = SendResult.fromString(injectionResponse.getMessageResults()[0].getErrorCode());
            newResponse.setResult(r);
        }

        if (injectionResponse.getMessageResults() != null && injectionResponse.getMessageResults().length > 0)
            newResponse.setAddressResults(injectionResponse.getMessageResults()[0].getAddressResults());

        return newResponse;

    }


    /**
     * Enumerated SendResult of the payload response from the Injection Api
     * @param responseDto The InjectionResponseDto from the Injection Api
     * @param responseCode The HTTP response code from the Injection Api
     * @return The SendResult from the Injection Api response
     */
    private SendResult DetermineSendResult(InjectionResponseDto responseDto, int responseCode) {

        switch (responseCode) {

            case 200: //HttpStatusCode.OK
                SendResult r = SendResult.fromString(responseDto.getErrorCode());
                if (r == null)
                    return SendResult.UnknownError;
                return r;

            case 500: //HttpStatusCode.InternalServerError
                return SendResult.InternalError;

            case 408: //HttpStatusCode.RequestTimeout
                return SendResult.Timeout;

            case 401: //HttpStatusCode.Unauthorized
                return SendResult.InvalidAuthentication;

            default:
                return SendResult.UnknownError;
        }
    }

}
