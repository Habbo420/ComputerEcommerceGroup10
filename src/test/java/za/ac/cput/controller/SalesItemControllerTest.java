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
import za.ac.cput.domain.Product;
import za.ac.cput.domain.Sales;
import za.ac.cput.domain.SalesItem;
import za.ac.cput.factory.ProductFactory;
import za.ac.cput.factory.SalesFactory;
import za.ac.cput.factory.SalesItemFactory;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SalesItemControllerTest {
    private static final List<Product> products = Arrays.asList(
            ProductFactory.buildTestProduct(1L),
            ProductFactory.buildTestProduct(2L)
    );

    private static final Sales sales = SalesFactory.buildTestSales(
            1L
    );

    private static final SalesItem salesItem = SalesItemFactory.buildSales(sales, products, products.size());

    @Autowired
    private TestRestTemplate restTemplate;

    private final String baseURL = "http://localhost:8080/salesItem";

    @Order(1)
    @Test
    @Transactional
    void a_create() {
        String url = baseURL + "/create";
        ResponseEntity<SalesItem> postResponse = restTemplate.postForEntity(url, salesItem, SalesItem.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        //assertEquals(postResponse.getStatusCode(), HttpStatus.OK);
        SalesItem savedSalesItem = postResponse.getBody();
        System.out.println("Saved data: " + savedSalesItem);
        assertEquals(savedSalesItem.getSalesItemID(), postResponse.getBody().getSalesItemID());
    }

    @Order(2)
    @Test
    @Transactional
    void read() {
        String url = baseURL + "/read/" + salesItem.getSalesItemID();
        System.out.println("URL: " + url);
        ResponseEntity<SalesItem> response = restTemplate.getForEntity(url, SalesItem.class);
        assertEquals(salesItem.getSalesItemID(), response.getBody().getSalesItemID());
        System.out.println(response.getBody());
    }

    @Order(3)
    @Test
    @Transactional
    void update() {
        SalesItem updated = new SalesItem.Builder().copy(salesItem).build();
        String url = baseURL + "/update";
        System.out.println("URL: " + url);
        System.out.println("Post data: " + updated);
        ResponseEntity<SalesItem> response = restTemplate.postForEntity(url, updated, SalesItem.class);
        assertNotNull(response.getBody());
    }

    @Order(5)
    @Test
    @Transactional
    @Disabled
    void delete() {
        String url = baseURL + "/delete/" + salesItem.getSalesItemID();
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