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
import za.ac.cput.domain.User;
import za.ac.cput.factory.UserFactory;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerTest {

    private static final User customer = UserFactory.buildCustomer(
            "David",
            "Garrancho",
            "DavidG@gmail.com",
            "Hol'emup"
    );

    @Autowired
    private TestRestTemplate restTemplate;

    private final String baseURL = "http://localhost:8080/user";

    @Test
    void a_create() {
        String url = baseURL + "/register";
        ResponseEntity<User> postResponse = restTemplate.postForEntity(url, customer, User.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        User savedCustomer = postResponse.getBody();
        System.out.println("Saved data: " + savedCustomer);
        assertEquals(savedCustomer.getCustomerID(), postResponse.getBody().getCustomerID());
    }

    @Test
    void b_read() {
        String url = baseURL + "/read/" + 4L;
        System.out.println("URL: " + url);
        ResponseEntity<User> response = restTemplate.getForEntity(url, User.class);
        assertEquals(customer.getCustomerID(), response.getBody().getCustomerID());
        System.out.println(response.getBody());
    }
    @Test
    void c_update() {
        User updated = new User.Builder()
                .copy(customer)
                .setFirstName("David")
                .setLastName("Smith")
                .setEmail("david@example.com")
                .build();

        String url = baseURL + "/update";
        System.out.println("URL: " + url);
        System.out.println("Post data: " + updated);

        ResponseEntity<User> response = restTemplate.postForEntity(url, updated, User.class);
        assertNotNull(response.getBody());
    }


    @Test
    @Disabled
    void e_delete() {
        String url = baseURL + "/delete/" + 3L;
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