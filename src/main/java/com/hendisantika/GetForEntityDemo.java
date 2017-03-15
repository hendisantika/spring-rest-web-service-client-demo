package com.hendisantika;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class GetForEntityDemo {
    public static void main(String args[]) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/spring-rest/data/fetch/{name}/{village}";
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "Hendi Santika");
        map.put("village", "Cimahi");
        ResponseEntity<Person> personEntity = restTemplate.getForEntity(url, Person.class, map);
        System.out.println("Name:"+personEntity.getBody().getName());
        System.out.println("Village:"+personEntity.getBody().getAddress().getVillage());
    }
}
