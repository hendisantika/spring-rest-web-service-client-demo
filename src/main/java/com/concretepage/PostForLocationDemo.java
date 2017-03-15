package com.concretepage;

import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class PostForLocationDemo {
    public static void main(String args[]) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/spring-rest/data/location/{id}/{name}";
		Address address = new Address("Jawa barat", "Cimahi", "UP");
		URI uri= restTemplate.postForLocation(url, address, 212, "Hendi Santika");
		System.out.println(uri.getPath());
    }
}
 