package za.ac.cput.controller;

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
import za.ac.cput.domain.Enquiry;
import za.ac.cput.factory.UserFactory;
import za.ac.cput.factory.EnquiryFactory;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class EnquiryControllerTest {

    private static final User customer = UserFactory.buildTestCustomer(
            4L
    );

    private static final Enquiry enquiry = EnquiryFactory.buildEnquiry(customer,"Delivery complaint","Did not get my delivery","Payed but did not receive my product", "20-09-2023");

    @Autowired
    private TestRestTemplate restTemplate;
    private final String baseURL = "http://localhost:8080/enquiry";

    @Test
    @Order(1)
    @Transactional
    void create() {
        String url = baseURL + "/create";
        ResponseEntity<Enquiry> postResponse = restTemplate.postForEntity(url, enquiry, Enquiry.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        //assertEquals(postResponse.getStatusCode(), HttpStatus.OK);
        Enquiry savedEnquiry = postResponse.getBody();
        System.out.println("Saved data: " + savedEnquiry);
        assertEquals(savedEnquiry.getEnquiryID(), postResponse.getBody().getEnquiryID());
    }


    @Test
    @Order(2)
    @Transactional
    void read() {
        String url = baseURL + "/read/" + enquiry.getEnquiryID();
        System.out.println("URL: " + url);
        ResponseEntity<Enquiry> response = restTemplate.getForEntity(url, Enquiry.class);
        assertEquals(enquiry.getEnquiryID(), response.getBody().getEnquiryID());
        System.out.println(response.getBody());
    }

    @Test
    @Order(3)
    @Transactional
    void update() {
        Enquiry updated = new Enquiry.Builder().copy(enquiry).setEnquirySubjectLine("Got the wrong delivery").build();
        String url = baseURL + "/update";
        System.out.println("Url: "+url);
        System.out.println("Post data: " + updated);
        ResponseEntity<Enquiry> response = restTemplate.postForEntity(url, updated, Enquiry.class);
        assertNotNull(response.getBody());
    }

    @Test
    @Order(4)
    @Transactional
    @Disabled
    void delete() {
        String url = baseURL + "/delete/" + enquiry.getEnquiryID();
        System.out.println("URL: "+url);
        restTemplate.delete(url);
    }

    @Test
    @Order(5)
    @Transactional
    void getAll() {
        String url = baseURL + "/getAll";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("Show All: ");
        System.out.println(response);
        System.out.println(response.getBody());
    }
}