package com.hendisantika;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class PostForEntityDemo {
    public static void main(String args[]) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/spring-rest/data/saveinfo/{id}/{name}";
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "212");
        map.put("name", "Hendi Santika");
		Address address = new Address("Jawa Barat", "Cimahi", "UP");
        ResponseEntity<Person> entity= restTemplate.postForEntity(url, address, Person.class, map);
        System.out.println(entity.getBody().getName());
        System.out.println(entity.getBody().getAddress().getVillage());
    }
}
 