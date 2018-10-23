package com.socketLabs.core;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {

    private HttpRequestMethod method;
    private String endPointUrl;
    private String body;
    private final Map<String, String> headers = new HashMap<>();

    public HttpRequest(HttpRequestMethod method, String endPointUrl) {
        this.method = method;
        this.endPointUrl = endPointUrl;
    }

    public void setBody(String value) {
        this.body = value;
    }
    public void setHeader(String key, String value) {
        this.headers.put(key, value);
    }

    /**
     * Send the HTTP Request
     * @return A SendResponse from the Injection Api response
     * @throws Exception in case of a network error.
     */
    public HttpResponse SendRequest() throws Exception {

        URL obj = new URL(this.endPointUrl);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add request method
        con.setRequestMethod(this.method.toString());

        //add request header
        for (Map.Entry<String, String> header : this.headers.entrySet()) {
            con.setRequestProperty(header.getKey(), header.getValue());
        }

        // Send request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(this.body);
        wr.flush();
        wr.close();

        // get response code.
        int responseCode = con.getResponseCode();

        System.out.println(String.format("\nSending '%s' request to URL : %s", this.method.toString(), this.endPointUrl));
        System.out.println("Request body : " + this.body);
        System.out.println("Response Code : " + responseCode);


        return new HttpResponse(responseCode, ParseStream(con.getInputStream()));
    }

    /**
     * Parse the Input stream from the HTTP Request
     * @param stream The input stream from the HTTP Request
     * @return Parsed JSON String
     * @throws IOException in case of a network error.
     */
    private String ParseStream(InputStream stream) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(stream));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

        return response.toString();
    }

}
