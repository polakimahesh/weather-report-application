package com.global.weather_report.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;

@Service
@Slf4j
public class HttpRequestService {
    private final CloseableHttpClient closeableHttpClient;

    public HttpRequestService(CloseableHttpClient closeableHttpClient) {
        this.closeableHttpClient = closeableHttpClient;
    }

    public ResponseDTO getRequest(String url){
        var request = new HttpGet(URI.create(url));
        return processRequestWithRetries(request);
    }
/**    This method attempts to process an HTTP request with retry logic.
        If the request fails (e.g., due to an IOException), it will retry up to 5 times.
        Once the request is successfully processed or the retry limit is reached, it returns a ResponseDTO.**/

    private ResponseDTO processRequestWithRetries(HttpUriRequestBase request) {
        int attemptsRemaining = 5;
        boolean shouldRetry;
        do {
            try {
                return processRequest(request);
            } catch (IOException e) {
                attemptsRemaining--;
                shouldRetry = attemptsRemaining > 0;
            }
        } while (shouldRetry);
        return new ResponseDTO("", 999);
    }

    private ResponseDTO processRequest(HttpUriRequestBase request) throws IOException {
        return closeableHttpClient.execute(request, response -> {
            var responseBody = "";
            if (response.getEntity() != null) {
                responseBody = new String(response.getEntity().getContent().readAllBytes());
            }
            if (response.getCode()!=200){
                log.info("URL : " + request.getRequestUri());
                log.info("METHOD : " + request.getMethod());
                try {
                    log.info("PAYLOAD : " + new String(request.getEntity().getContent().readAllBytes()));
                } catch (IOException | NullPointerException e) {
                    log.info("PAYLOAD : ");
                }
                log.info("STATUS : " + response.getCode());
                log.info("RESPONSE BODY : " + responseBody);
            }
            return new ResponseDTO(responseBody, response.getCode());
        });
    }
}
