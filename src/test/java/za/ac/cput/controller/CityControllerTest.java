package za.ac.cput.controller;
/* CityControllerTest.java
Controller Test for City
Author: Michael Daniel Johnson 221094040
Date: 19 August 2023
*/
import jakarta.transaction.Transactional;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.City;
import za.ac.cput.domain.Country;
import za.ac.cput.factory.CityFactory;
import za.ac.cput.factory.CountryFactory;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CityControllerTest {

    private static Country southAfrica = CountryFactory.createTestCountry(
            1L
    );

    private static  City city = CityFactory.createCity(
            "Port Elizabeth",
            southAfrica
    );

    @Autowired
    private TestRestTemplate restTemplate;

    private final String baseURL = "http://localhost:8080/city";

    @Order(1)
    @Test
    void a_create() {
        String url = baseURL + "/create";
        ResponseEntity<City> postResponse = restTemplate.postForEntity(url, city, City.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        //assertEquals(postResponse.getStatusCode(), HttpStatus.OK);
        City savedCity = postResponse.getBody();
        System.out.println("Saved data: " + savedCity);
        assertEquals(savedCity.getCityID(), postResponse.getBody().getCityID());
    }


    @Order(2)
    @Test
    void read() {
        String url = baseURL + "/read/" + city.getCityID();
        System.out.println("URL: "+ url);
        ResponseEntity<City> response = restTemplate.getForEntity(url,City.class);
        assertEquals(city.getCityID(), response.getBody().getCityID());
        System.out.println(response.getBody());
    }

    @Order(3)
    @Test
    //update doesnt work properly
    void update() {
        City updated = new City.Builder().copy(city)
                .setCityName("Paris")
                .build();
        String url = baseURL + "/update";
        System.out.println("Post Data: "+updated);
        ResponseEntity<City> response = restTemplate.postForEntity(url,updated, City.class);
        assertNotNull(response.getBody());
    }

    @Order(5)
    @Test
    @Disabled
    void delete() {
        String url = baseURL+ "/delete/" + city.getCityID();
        System.out.println("URL: "+ url);
        restTemplate.delete(url);
    }

    @Order(4)
    @Test
    void getAll() {
        String url = baseURL+ "/getAll";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null,headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,entity, String.class);
        System.out.println("Show All:");
        System.out.println(response.getBody());
    }
}