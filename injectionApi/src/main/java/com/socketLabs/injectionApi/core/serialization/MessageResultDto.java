package com.socketLabs.injectionApi.core.serialization;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.socketLabs.injectionApi.AddressResult;

import java.util.List;

/**
 * Data transfer object representing a message result from the Injection Api.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
class MessageResultDto {

    /** Index of message being sent. */
    @JsonProperty("Index")
    private int index;

    /** The resulting response ErrorCode of the Injection Api send request */
    @JsonProperty("ErrorCode")
    private String errorCode;

    /** A List of AddressResult objects that contain the status of each address that failed. If no messages failed this array is empty. */
    @JsonProperty("AddressResults")
    private List<AddressResult> addressResults;

    /**
     * Get the index of message being sent
     * @return
     */
    public int getIndex() {
        return index;
    }
    /**
     * Set the index of message being sent
     * @param value int
     */
    public void setIndex(int value) {
        this.index = value;
    }

    /**
     * Get the response ErrorCode
     * @return String
     */
    public String getErrorCode() {
        return errorCode;
    }
    /**
     * Set response ErrorCode
     * @param value String
     */
    public void setErrorCode(String value) {
        this.errorCode = value;
    }

    /**
     * Get the List of AddressResult objects
     * @return List<AddressResult>
     */
    public List<AddressResult> getAddressResults() {
        return addressResults;
    }
    /**
     * Set the List of AddressResult objects
     * @param value List<AddressResult>
     */
    public void setAddressResults(List<AddressResult> value) {
        this.addressResults = value;
    }
}
