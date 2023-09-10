package za.ac.cput.controller;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.*;
import za.ac.cput.factory.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
SupplierOrderControllerTest.java
Controller Test for SupplierOrderController
Author: Ethan Botes (220101299)
Date: 20/08/2023
 */

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class SupplierOrderControllerTest {

    private final static Country southAfrica = CountryFactory.createCountry(
            "South Africa"
    );
    private final static City homeCity = CityFactory.createCity(
            "Cape Town",
            southAfrica
    );
    private final static Address homeAddress = AddressFactory.buildAddress(
            "53 Main Road",
            "6045",
            homeCity
    );

    private final static Supplier supplier = SupplierFactory.buildSupplier
            ("supplier@intel.com",
                    "0214564389",
                    homeAddress,
                    "Intel");

    private static final List<Product> products = Arrays.asList(
            ProductFactory.buildProduct("FX 3060", "Item", "Next Generation gaming with the RTX 3060 TI", 3000.00),
            ProductFactory.buildProduct("RX 4050", "Item", "Next Generation gaming with the RTX 4050", 4800.00)
    );

    private static final SupplierOrder supplierOrder = SupplierOrderFactory.buildSupplierOrder
            ("06-01-2022", "12-01-2022", "11-01-2022",
                    120.00, 12120.00, 2, 6000.00, products, supplier);

    @Autowired
    private TestRestTemplate restTemplate;

    private final static String baseURL = "http://localhost:8080/supplierOrder";

    @Order(1)
    @Test
    void create() {
        String url = baseURL + "/create";
        ResponseEntity<SupplierOrder> postResponse = restTemplate.postForEntity(url, supplierOrder, SupplierOrder.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        SupplierOrder savedSupplierOrder = postResponse.getBody();
        System.out.println("Saved data: " + savedSupplierOrder);
        assertEquals(supplierOrder.getOrderID(), postResponse.getBody().getOrderID());

    }

    @Test
    @Order(2)
    void read() {
        String url = baseURL + "/read/" + supplierOrder.getOrderID();
        System.out.println("URL: " + url);
        ResponseEntity<SupplierOrder> response = restTemplate.getForEntity(url, SupplierOrder.class);
        assertEquals(supplierOrder.getOrderID(), response.getBody().getOrderID());
        System.out.println(response.getBody());
    }

    @Test
    void update() {
        SupplierOrder updated = new SupplierOrder.Builder().copy(supplierOrder).setProductIndividualPrice(12000).build();
        String url = baseURL + "/update";
        System.out.println("URL: " + url);
        System.out.println("Post data: " + updated);
        ResponseEntity<SupplierOrder> response = restTemplate.postForEntity(url, updated, SupplierOrder.class);
        assertNotNull(response.getBody());
    }

    @Test
    @Disabled
    void delete() {
        String url = baseURL + "/delete/" + supplierOrder.getOrderID();
        System.out.println("URL: " + url);
        restTemplate.delete(url);

    }
    @Test
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