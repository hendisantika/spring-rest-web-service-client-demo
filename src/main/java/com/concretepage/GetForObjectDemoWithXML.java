package com.concretepage;

import org.springframework.web.client.RestTemplate;

public class GetForObjectDemoWithXML {
    public static void main(String args[]) {
        RestTemplate restTemplate = new RestTemplate();
        Company company = restTemplate.getForObject("http://localhost:8080/spring-rest/data/fetchxml/{id}", Company.class, 200);
        System.out.println("ID: " + company.getId());
        System.out.println("Company: " + company.getCompanyName());
        System.out.println("CEO: " + company.getCeoName());
    }
}
