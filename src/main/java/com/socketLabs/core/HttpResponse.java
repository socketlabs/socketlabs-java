package com.socketLabs.core;

import java.io.InputStream;

public class HttpResponse {

    private int responseCode;
    private String responseMessage;
    private String content;

    public int getResponseCode() {
        return responseCode;
    }
    public void setResponseCode(int value) {
        this.responseCode = value;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
    public void setResponseMessage(String value) {
        this.responseMessage = value;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String value) {
        this.content = value;
    }

    public HttpResponse(int responseCode, String content) {
        this.setResponseCode(responseCode);
        this.setContent(content);
    }

}
