package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.City;
import za.ac.cput.domain.Country;
import za.ac.cput.domain.StoreDetails;
import za.ac.cput.factory.AddressFactory;
import za.ac.cput.factory.CityFactory;
import za.ac.cput.factory.CountryFactory;
import za.ac.cput.factory.StoreDetailsFactory;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StoreDetailsControllerTest {

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

    private static final StoreDetails storeDetails = StoreDetailsFactory.buildTestStoreDetails(
            "123",
            "Evetech",
            homeAddress,
            "021 445 9912",
            "techEve@gmail.com"
    );

    @Autowired
    private TestRestTemplate restTemplate;

    private final String baseURL = "http://localhost:8080/storeDetails";

    @Test
    @Order(1)
    void create() {
        String url = baseURL + "/create";
        ResponseEntity<StoreDetails> postResponse = restTemplate.postForEntity(url, storeDetails, StoreDetails.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        StoreDetails savedCustomer = postResponse.getBody();
        System.out.println("Saved data: " + savedCustomer);
        assertEquals(storeDetails.getStoreID(), postResponse.getBody().getStoreID());
    }

    @Test
    @Order(2)
    void read() {
        String url = baseURL + "/read/" + storeDetails.getStoreID();
        System.out.println("URL: " + url);
        ResponseEntity<StoreDetails> response = restTemplate.getForEntity(url, StoreDetails.class);
        assertEquals(storeDetails.getStoreID(), response.getBody().getStoreID());
        System.out.println(response.getBody());
    }

    @Test
    @Order(3)
    void update() {
        StoreDetails updated = new StoreDetails.Builder().copy(storeDetails)
                .setStoreName("N Computers")
                .build();
        String url = baseURL + "/update";
        System.out.println("URL: " + url);
        System.out.println("Post data: " + updated);
        ResponseEntity<StoreDetails> response = restTemplate.postForEntity(url, updated, StoreDetails.class);
        assertNotNull(response.getBody());
    }

    @Test
    @Order(4)
    @Disabled
    void delete() {
        String url = baseURL + "/delete/" + storeDetails.getStoreID();
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