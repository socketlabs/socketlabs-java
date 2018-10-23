package com.socketLabs.core.serialization;

import com.socketLabs.AddressResult;

import java.util.List;

public class MessageResultDto {

    /** Index of message being sent. */
    private int index;

    /** The resulting response ErrorCode of the Injection Api send request */
    private String errorCode;

    /** An array of AddressResult objects that contain the status of each address that failed. If no messages failed this array is empty. */
    private List<AddressResult> addressResults;

    public int getIndex() {
        return index;
    }
    public void setIndex(int value) {
        this.index = value;
    }

    public String getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(String value) {
        this.errorCode = value;
    }

    public List<AddressResult> getAddressResults() {
        return addressResults;
    }
    public void setAddressResults(List<AddressResult> value) {
        this.addressResults = value;
    }
}
