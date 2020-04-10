package com.assignment.onlinebookstore.Utils;

import org.springframework.retry.annotation.*;
import org.springframework.stereotype.*;

import java.io.*;
import java.net.*;

@Component
public class RestUtils {

    @Retryable(
            value = {Exception.class},
            maxAttempts = 4,
            backoff = @Backoff(delay = 10000, maxDelay = 120000, multiplier = 2))
    public static StringBuffer makeGetCall(String urlString) throws Exception {
        String urlWithoutSpaces = urlString.replaceAll(" ","%20");
        URL url = new URL(urlWithoutSpaces);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        StringBuffer response = null;
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setDoOutput(true);

        int responseCode = httpURLConnection.getResponseCode();
        String responseMessage = httpURLConnection.getResponseMessage();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    httpURLConnection.getInputStream()));
            String inputLine;

            response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        }

        if(response==null)
            throw new Exception("Failed to Connect to "+urlString);

        return response;
    }
}
