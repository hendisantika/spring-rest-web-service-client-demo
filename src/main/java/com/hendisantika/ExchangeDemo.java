package com.hendisantika;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ExchangeDemo {
    public static void main(String args[]) {
        RestTemplate restTemplate = new RestTemplate();
		String uri = "http://localhost:8080/spring-rest/data/exchange/{id}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>("Hello World!", headers);
        ResponseEntity<Person> personEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, Person.class, 100);
        System.out.println("ID:"+personEntity.getBody().getId());
        System.out.println("Name:"+personEntity.getBody().getName());
        System.out.println("Village:"+personEntity.getBody().getAddress().getVillage());
    }
}
