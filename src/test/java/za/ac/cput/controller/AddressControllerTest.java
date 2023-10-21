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
    private static Country southAfrica = CountryFactory.createTestCountry(
            1L
    );
    private static  City homeCity = CityFactory.createTestCity(
            1L
    );
    private static  Address address = AddressFactory.buildAddress(
            "53 Main Road",
            "6045",
            homeCity
    );

    @Autowired
    private TestRestTemplate restTemplate;
    private final String baseURL = "http://localhost:8080/address" ;


    @Order(1)
    @Test
    void a_create() {
        String url = baseURL + "/create";
        ResponseEntity<Address> postResponse = restTemplate.postForEntity(url, address, Address.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        //assertEquals(postResponse.getStatusCode(), HttpStatus.OK);
        Address savedCountry = postResponse.getBody();
        System.out.println("Saved data: " + savedCountry);
        assertEquals(savedCountry.getAddressID(), postResponse.getBody().getAddressID());
    }

    @Order(2)
    // @Transactional
    @Test
    void read() {
        String url = baseURL + "/read/" + address.getAddressID();
        System.out.println("URL: "+ url);
        ResponseEntity<Address> response = restTemplate.getForEntity(url, Address.class);
        assertEquals(address.getAddressID(), response.getBody().getAddressID());
        System.out.println(response.getBody());
    }

    @Order(3)
    //@Transactional
    @Test
    void update() {
        Address updated = new Address.Builder().copy(address)
                .build();
        String url = baseURL + "/update";
        System.out.println("Post Data: "+ updated);
        ResponseEntity<Address> response = restTemplate.postForEntity(url, updated, Address.class);
        assertNotNull(response.getBody());

    }

    @Order(5)
    // @Transactional
    @Test
    @Disabled
    void delete() {
        String url = baseURL + "/delete/" + address.getAddressID();
        System.out.println("URL: "+ url);
        restTemplate.delete(url);
    }

    @Order(4)
    // @Transactional
    @Test
    void getAll() {
        String url = baseURL +"/getAll";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null,headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,entity,String.class);
        System.out.println("Show All:");
        System.out.println(response.getBody());
    }
}