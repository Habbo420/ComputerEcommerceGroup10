package za.ac.cput.controller;

/* CustomerControllerTest.java
Test for Controller for Customer
Author: David Henriques Garrancho (221475982)
Date: 17 June 2023
*/

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.domain.Customer;
import za.ac.cput.factory.CustomerFactory;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerTest {

    private static Customer customer = CustomerFactory.buildTestCustomer(
            "e0295579-70a0-48f3-b0c8-3f3fbe66b6cc",
            "Luke",
            "Ben",
            "LW@gmail.com",
            "wufh%2465"
    );

    @Autowired
    private TestRestTemplate restTemplate;

    private final String baseURL = "http://localhost:8080/customer";

    @Test
    void a_create() {
        String url = baseURL + "/create";
        ResponseEntity<Customer> postResponse = restTemplate.postForEntity(url, customer, Customer.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        //assertEquals(postResponse.getStatusCode(), HttpStatus.OK);
        Customer savedCustomer = postResponse.getBody();
        System.out.println("Saved data: " + savedCustomer);
        assertEquals(customer.getCustomerID(), postResponse.getBody().getCustomerID());
    }

    @Test
    void b_read() {
        String url = baseURL + "/read/" + customer.getCustomerID();
        System.out.println("URL: " + url);
        ResponseEntity<Customer> response = restTemplate.getForEntity(url, Customer.class);
        assertEquals(customer.getCustomerID(), response.getBody().getCustomerID());
        System.out.println(response.getBody());
    }
    @Test
    void c_update() {
        Customer updated = new Customer.Builder().copy(customer).setFirstName("Ethan Andrew").build();
        String url = baseURL + "/update";
        System.out.println("URL: " + url);
        System.out.println("Post data: " + updated);
        ResponseEntity<Customer> response = restTemplate.postForEntity(url, updated, Customer.class);
        assertNotNull(response.getBody());
    }

    @Test
    @Disabled
    void e_delete() {
        String url = baseURL + "/delete/" + customer.getCustomerID();
        System.out.println("URL: " + url);
        restTemplate.delete(url);
    }

    @Test
    void d_getAll() {
        String url = baseURL + "/getAll";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("Show ALL:");
        System.out.println(response);
        System.out.println(response.getBody());
    }
}