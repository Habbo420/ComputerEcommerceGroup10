package za.ac.cput.controller;

/* SalesControllerTest.java
Test for Controller for Sales
Author: David Henriques Garrancho (221475982)
Date: 16 August 2023
*/

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.User;
import za.ac.cput.domain.Sales;
import za.ac.cput.factory.UserFactory;
import za.ac.cput.factory.SalesFactory;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class SalesControllerTest {
    private static final User customer = UserFactory.buildTestCustomer(
            14L
    );


    private static final Sales sales = SalesFactory.buildSales(
            "16-08-2023",
            4560.00,
            customer
    );

    @Autowired
    private TestRestTemplate restTemplate;

    private final String baseURL = "http://localhost:8080/sales";

    @Order(1)
    @Test
    @Transactional
    void a_create() {
        String url = baseURL + "/create";
        ResponseEntity<Sales> postResponse = restTemplate.postForEntity(url, sales, Sales.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        //assertEquals(postResponse.getStatusCode(), HttpStatus.OK);
        Sales savedSales = postResponse.getBody();
        System.out.println("Saved data: " + savedSales);
        assertEquals(savedSales.getSaleID(), postResponse.getBody().getSaleID());
    }

    @Order(2)
    @Test
    @Transactional
    void read() {
        String url = baseURL + "/read/" + sales.getSaleID();
        System.out.println("URL: " + url);
        ResponseEntity<Sales> response = restTemplate.getForEntity(url, Sales.class);
        assertEquals(sales.getSaleID(), response.getBody().getSaleID());
        System.out.println(response.getBody());
    }

    @Order(3)
    @Test
    @Transactional
    void update() {
        Sales updated = new Sales.Builder().copy(sales).setTotalAmount(3560.00).build();
        String url = baseURL + "/update";
        System.out.println("URL: " + url);
        System.out.println("Post data: " + updated);
        ResponseEntity<Sales> response = restTemplate.postForEntity(url, updated, Sales.class);
        assertNotNull(response.getBody());
    }

    @Order(5)
    @Test
    @Transactional
    @Disabled
    void delete() {
        String url = baseURL + "/delete/" + sales.getSaleID();
        System.out.println("URL: " + url);
        restTemplate.delete(url);
    }

    @Order(4)
    @Test
    @Transactional
    void getAll() {
        String url = baseURL + "/getAll";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("Show ALL:");
        System.out.println(response);
        System.out.println(response.getBody());
    }
}