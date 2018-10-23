package com.socketLabs.core.serialization;

import com.socketLabs.AddressResult;

public class MessageResultDto {

    /// <summary>
    /// Index of message being sent.
    /// </summary>
    public int index;

    /// <summary>
    /// The resulting response ErrorCode of the Injection Api send request
    /// </summary>
    public String errorCode;

    /// <summary>
    /// An array of AddressResult objects that contain the status of each address that failed. If no messages failed this array is empty.
    /// </summary>
    public AddressResult[] addressResults;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public AddressResult[] getAddressResults() {
        return addressResults;
    }

    public void setAddressResults(AddressResult[] addressResults) {
        this.addressResults = addressResults;
    }
}
