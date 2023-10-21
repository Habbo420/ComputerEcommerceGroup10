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
import za.ac.cput.factory.*;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InvoiceControllerTest {


    private static  Sales sales = SalesFactory.buildTestSales(
            1L
    );
    private static  StoreDetails storeDetails = StoreDetailsFactory.buildTestStoreDetails(
            1L
    );

    private static  Invoice invoice = InvoiceFactory.buildInvoice(
            storeDetails,
            sales
    );

    @Autowired
    private TestRestTemplate restTemplate;


    private final String baseURL = "http://localhost:8080/invoice" ;


    @Test
    @Order(1)
    @Transactional  // Ensure this annotation is present
    void create() {
        String url = baseURL + "/create";
        ResponseEntity<Invoice> postResponse = restTemplate.postForEntity(url, invoice, Invoice.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        //assertEquals(postResponse.getStatusCode(), HttpStatus.OK);
        Invoice savedInvoice= postResponse.getBody();
        System.out.println("Saved data: " + savedInvoice);
        assertEquals(savedInvoice.getInvoiceNumber(), postResponse.getBody().getInvoiceNumber());
    }

    @Test
    @Order(2)
    void read() {
        String url = baseURL + "/read/" + invoice.getInvoiceNumber();
        System.out.println("URL: " + url);
        ResponseEntity<Invoice> response = restTemplate.getForEntity(url, Invoice.class);
        assertEquals(invoice.getInvoiceNumber(), response.getBody().getInvoiceNumber());
        System.out.println(response.getBody());
    }

    @Test
    @Order(3)
    @Disabled
    void update() {
        Invoice updated = new Invoice.Builder().copy(invoice).build();
        String url = baseURL + "/update";
        System.out.println("URL: " + url);
        System.out.println("Post data: " + updated);
        ResponseEntity<Invoice> response = restTemplate.postForEntity(url, updated, Invoice.class);
        assertNotNull(response.getBody());
    }

    @Test
    @Order(4)
    @Disabled
    void delete() {
        String url = baseURL + "/delete/" + invoice.getInvoiceNumber();
        System.out.println("URL: " + url);
        restTemplate.delete(url);
    }

    @Test
    @Order(5)
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