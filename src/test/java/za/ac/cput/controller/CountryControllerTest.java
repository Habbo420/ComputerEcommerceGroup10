package za.ac.cput.controller;

/* CountryControllerTest.java
Controller test class for CountryController.java
Author: Michael Daniel Johnson 221094040
Date: 19 August 2023
*/

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Country;
import za.ac.cput.factory.CountryFactory;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Transactional
class CountryControllerTest {

    private static final Country country = CountryFactory.createCountry("South Africa");

    @Autowired
    private TestRestTemplate restTemplate;

    private final String baseURL = "http://localhost:8080/country";

    @Order(1)
    @Test
    void a_create() {
        String url = baseURL + "/create";
        ResponseEntity<Country> postResponse = restTemplate.postForEntity(url, country, Country.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        Country savedCountry = postResponse.getBody();
        System.out.println("Saved data: " + savedCountry);
        assertEquals(savedCountry.getCountryID(), postResponse.getBody().getCountryID());
    }

    @Order(2)
    @Test
    void read() {
        String url = baseURL + "/read/" + country.getCountryID();
        System.out.println("URL: "+ url);
        ResponseEntity<Country> response = restTemplate.getForEntity(url, Country.class);
        assertEquals(country.getCountryID(), response.getBody().getCountryID());
        System.out.println(response.getBody());
    }

    @Order(3)
    @Test
    void update() {
        Country updated = new Country.Builder().copy(country)
                .setCountryName("Finland")
                .build();
        String url = baseURL + "/update";
        System.out.println("Post Data: "+ updated);
        ResponseEntity<Country> response = restTemplate.postForEntity(url, updated, Country.class);
        assertNotNull(response.getBody());

    }

    @Order(5)
    @Test
    @Disabled
    void delete() {
        String url = baseURL + "/delete/" + country.getCountryID();
        System.out.println("URL: "+ url);
        restTemplate.delete(url);
    }

    @Order(4)
    @Test
    void getAll() {
        String url = baseURL +"/getAll";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null,headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,entity,String.class);
        System.out.println("Show All:");
        System.out.println(response.getBody());
    }
}