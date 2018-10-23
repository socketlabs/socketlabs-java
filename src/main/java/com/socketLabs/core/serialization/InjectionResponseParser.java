package com.socketLabs.core.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.socketLabs.SendResponse;
import com.socketLabs.SendResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InjectionResponseParser {

    // TODO: Parse the Response from the Injection API to SendResponse


    /// <summary>
    /// Parse the response from theInjection Api into <c>SendResponse</c>
    /// </summary>
    /// <param name="httpResponse">The <c>HttpResponseMessage</c> from the Injection Api</param>
    /// <returns>A <c>SendResponse</c> from the Injection Api response</returns>
    public SendResponse Parse(InputStream stream) throws IOException {

        String contentString = ParseStream(stream);

        ObjectMapper mapper = new ObjectMapper();



        //SimpleModule module = new SimpleModule();
        //module.addDeserializer(Item.class, new ItemDeserializer());
        //mapper.registerModule(module);

        //Item readValue = mapper.readValue(json, Item.class);


        //mapper..writeValueAsString();

        /*
        var injectionResponse = JsonConvert.DeserializeObject<InjectionResponseDto>(contentString);


        var resultEnum = DetermineSendResult(injectionResponse, httpResponse);
        var newResponse = new SendResponse
        {
            Result = resultEnum,
                    TransactionReceipt = injectionResponse.TransactionReceipt
        };

        if (resultEnum == SendResult.Warning && (injectionResponse.MessageResults != null && injectionResponse.MessageResults.Length > 0))
        {
            System.Enum.TryParse(injectionResponse.MessageResults[0].ErrorCode, true, out SendResult errorCode);
            newResponse.Result = errorCode;
        }

        if (injectionResponse.MessageResults != null && injectionResponse.MessageResults.Length > 0)
            newResponse.AddressResults = injectionResponse.MessageResults[0].AddressResults;

        return newResponse;
    */
        return null;
    }


    private String ParseStream(InputStream stream) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(stream));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

        return response.toString();
    }

    /// <summary>
    /// Enumerated SendResult of the payload response from the Injection Api
    /// </summary>
    /// <param name="responseDto">The <c>InjectionResponseDto</c> from the Injection Api</param>
    /// <param name="httpResponse">The <c>HttpResponseMessage</c> from the Injection Api</param>
    /// <returns>The <c>SendResult</c> from the Injection Api response</returns>
    private SendResult DetermineSendResult(InjectionResponseDto responseDto, int httpResponseCode) {
        switch (httpResponseCode) {
            case 200: //HttpStatusCode.OK
                //if (!System.Enum.TryParse(responseDto.ErrorCode, true, out SendResult errorCode))

                    return SendResult.UnknownError;
                //return errorCode;

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
