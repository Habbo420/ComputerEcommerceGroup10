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
import za.ac.cput.domain.*;
import za.ac.cput.factory.UserFactory;
import za.ac.cput.factory.ProductFactory;
import za.ac.cput.factory.ReviewFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class ReviewControllerTest {

    private static final Product product = ProductFactory.buildTestProduct(2L);
    private static final User customer = UserFactory.buildTestCustomer(
            3L
    );

    private static final Review review = ReviewFactory.buildReview(3, product, customer);


    @Autowired
    private TestRestTemplate restTemplate;

    private final String baseURL = "http://localhost:8080/review";

    @Test
    @Transactional
    void a_create() {
        String url = baseURL + "/create";
        ResponseEntity<Review> postResponse = restTemplate.postForEntity(url, review, Review.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        Review savedReview = postResponse.getBody();
        System.out.println("Saved data: " + savedReview);
        assertEquals(savedReview.getReviewID(), postResponse.getBody().getReviewID());
    }


    @Test
    @Transactional
    void b_read() {
        String url = baseURL + "/read/" + review.getReviewID();
        System.out.println("URL: " + url);
        ResponseEntity<Review> response = restTemplate.getForEntity(url, Review.class);
        assertEquals(review.getReviewID(), response.getBody().getReviewID());
        System.out.println(response.getBody());
    }


    @Test
    @Transactional
    void c_update() {
        Review updated = new Review.Builder().copy(review).setRating(8).build();
        String url = baseURL + "/update";
        System.out.println("URL: " + url);
        System.out.println("Post data: " + updated);
        ResponseEntity<Review> response = restTemplate.postForEntity(url, updated, Review.class);
        assertNotNull(response.getBody());
    }


    @Test
    @Transactional
    @Disabled
    void e_delete() {
        String url = baseURL + "/delete/" + review.getReviewID();
        System.out.println("URL: " + url);
        restTemplate.delete(url);
    }

    @Order(4)
    @Test
    @Transactional
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
