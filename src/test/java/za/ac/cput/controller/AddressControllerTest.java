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
import za.ac.cput.factory.AddressFactory;
import za.ac.cput.factory.CityFactory;
import za.ac.cput.factory.CountryFactory;
import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class AddressControllerTest {
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

    @Autowired
    private TestRestTemplate restTemplate;
    private final String baseURL = "http://localhost:8080/address" ;


    @Order(1)
    @Test
    @Transactional
    void create() {
        String url = baseURL + "/create";
        ResponseEntity<Address> postResponse = restTemplate.postForEntity(url, homeAddress, Address.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());

        Address savedAddress = postResponse.getBody();
        System.out.println("Saved data: " + savedAddress);

        assertEquals(homeAddress.getAddressID(), postResponse.getBody().getAddressID());
    }

    @Order(2)
    @Test
    @Transactional
    void read() {
        String url = baseURL + "/read/" + homeAddress.getAddressID();
        System.out.println("URL: " + url);
        ResponseEntity<Address> response = restTemplate.getForEntity(url, Address.class);
        assertEquals(homeAddress.addressID, response.getBody().addressID);
        System.out.println(response.getBody());
    }

    @Order(3)
    @Test
    @Transactional
    void update() {
         Address homeAddress = AddressFactory.buildAddress(
                "53 Main Road",
                "6045",
                homeCity
        );
         Address updatedAddress = new Address.Builder().copy(homeAddress)
                 .setStreetAddress("6 Basset Road")
                 .build();
        String url = baseURL + "/update";
        System.out.println("URL: " + url);
        System.out.println("Post data: " + updatedAddress);
        ResponseEntity<Address> response = restTemplate.postForEntity(url, updatedAddress, Address.class);
        assertNotNull(response.getBody());
    }

    @Order(5)
    @Test
    @Transactional
    @Disabled
    void delete() {
        String url = baseURL + "/delete/" + homeAddress.getAddressID();
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