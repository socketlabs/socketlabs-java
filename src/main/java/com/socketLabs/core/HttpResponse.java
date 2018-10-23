package com.socketLabs.core;

import java.io.InputStream;

public class HttpResponse {

    private int responseCode;
    private String content;

    public int getResponseCode() {
        return responseCode;
    }

    public String getContent() {
        return content;
    }

    public HttpResponse(int responseCode, String content) {
        this.responseCode = responseCode;
        this.content = content;
    }

}
