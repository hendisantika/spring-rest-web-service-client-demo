package com.hendisantika;

import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

public class HeadForHeadersDemo {
    public static void main(String args[]) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/spring-rest/data/fetch/{id}";
        HttpHeaders httpHeaders = restTemplate.headForHeaders(url, 100);
        System.out.println(httpHeaders.getDate());
        System.out.println(httpHeaders.getContentType());
    }
}
 