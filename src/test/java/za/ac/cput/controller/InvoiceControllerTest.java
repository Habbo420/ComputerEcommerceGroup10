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
import za.ac.cput.service.impl.StoreDetailsServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InvoiceControllerTest {

    private static final Country southAfrica = CountryFactory.createCountry(
            "South Africa"
    );
    private static final City homeCity = CityFactory.createCity(
            "Cape Town",
            southAfrica
    );
    private static final Address homeAddress = AddressFactory.buildAddress(
            "53 Main Road",
            "6045",
            homeCity
    );

    private static final Customer customer = CustomerFactory.buildCustomer(
            "Jason",
            "King",
            "KingJason@gmail.com",
            "AlexDraai143"
    );
    private static final Sales sales = SalesFactory.buildSales(
            "05-08-2023",
            7000.00,
            customer
    );
    private static final StoreDetails storeDetails = StoreDetailsFactory.buildStoreDetails(
            "CapConTech",
            homeAddress,
            "021 445 9912",
            "CapConTech@gmail.com"
    );

    private static final Invoice invoice = InvoiceFactory.buildInvoice(
            storeDetails,
            sales
    );

    @Autowired
    private TestRestTemplate restTemplate;


    private final String baseURL = "http://localhost:8080/invoice" ;


    @Order(1)
    @Test
    void create() {
        String url = baseURL + "/create";
        ResponseEntity<Invoice> postResponse = restTemplate.postForEntity(url, invoice, Invoice.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());

        Invoice savedInvoice = postResponse.getBody();
        System.out.println("Saved data: " + savedInvoice);

        assertEquals(invoice.getInvoiceNumber(), postResponse.getBody().getInvoiceNumber());
    }

    @Order(2)
    @Test
    void read() {
        String url = baseURL + "/read/" + invoice.getInvoiceNumber();
        System.out.println("URL: " + url);
        ResponseEntity<Invoice> response = restTemplate.getForEntity(url, Invoice.class);
        assertEquals(invoice.getInvoiceNumber(), response.getBody().getInvoiceNumber());
        System.out.println(response.getBody());
    }

    @Order(3)
    @Test
    void update() {
        Invoice updated = new Invoice.Builder().copy(invoice).setStoreDetails(storeDetails).build();
        String url = baseURL + "/update";
        System.out.println("URL: " + url);
        System.out.println("Post data: " + updated);
        ResponseEntity<Invoice> response = restTemplate.postForEntity(url, updated, Invoice.class);
        assertNotNull(response.getBody());
    }

    @Order(5)
    @Test
    @Disabled
    void delete() {
        String url = baseURL + "/delete/" + invoice.getInvoiceNumber();
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
        //System.out.println(response);
        System.out.println(response.getBody());
    }


}