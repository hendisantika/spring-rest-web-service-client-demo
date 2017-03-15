package com.hendisantika;

import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class PutDemo {
    public static void main(String args[]) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/spring-rest/data/putdata/{id}/{name}";
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "411");
        map.put("name", "Santika");
        Address address = new Address("Jawa Barat", "Cimahi","UP");
        restTemplate.put(url, address, map);
    }
}
