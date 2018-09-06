package com.hendisantika.currency;

import org.springframework.web.client.RestTemplate;

/**
 * Created by hendisantika on 5/1/17.
 */
public class App {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        Currency currency = restTemplate.getForObject("http://api.fixer.io/latest?base={from}&symbols={to}", Currency.class, 200);
        System.out.println("Base: " + currency.getBase());
        System.out.println("Date: " + currency.getDate());
        System.out.println("Rates: " + currency.getRates());
    }
}
