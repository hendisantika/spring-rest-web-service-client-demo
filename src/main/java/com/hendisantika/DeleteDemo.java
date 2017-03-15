package com.hendisantika;

import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class DeleteDemo {
    public static void main(String args[]) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/spring-rest/data/delete/{name}/{village}";
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "Hendi Santika");
        map.put("village", "Cimahi");
        restTemplate.delete(url, map);
    }
}
