package za.ac.cput.service.impl;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.City;
import za.ac.cput.domain.Country;
//import za.ac.cput.domain.Invoice;
import za.ac.cput.factory.AddressFactory;
import za.ac.cput.factory.CityFactory;
import za.ac.cput.factory.CountryFactory;
import za.ac.cput.repository.AddressRepository;
import za.ac.cput.service.AddressService;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@Transactional
class AddressServiceImplTest {

    @Autowired
    private AddressService addressService;
    @Autowired
    private AddressRepository addressRepository;
    private static Country southAfrica = CountryFactory.createCountry(
            "South Africa"
    );
    private static  City homeCity = CityFactory.createCity(
            "Cape Town",
            southAfrica
    );
    private static  Address homeAddress = AddressFactory.buildAddress(
            "53 Main Road",
            "6045",
            homeCity
    );

    @Test
    @Order(1)
    void create() {
        Address created = addressRepository.save(homeAddress);
        assertEquals(homeAddress.getAddressID(), created.addressID);
        System.out.println("Created " + created);
    }

    @Test
    @Order(2)
    void read() {
        Address read = addressService.read(homeAddress.addressID);
        assertNotNull(read);
        System.out.println("Read " + read);
    }

    @Test
    @Order(3)
    void update() {
        Address homeAddress = AddressFactory.buildAddress(
                "53 Main Road",
                "6045",
                homeCity
        );

        Address updated = new Address.Builder().copy(homeAddress)
                .setStreetAddress("6 Basset Road")
                .build();
        assertNotNull(addressService.update(homeAddress));
        System.out.println("Updated " + updated);
    }

    @Test
    @Order(5)
    void delete() {
        boolean delete = addressService.delete(homeAddress.getAddressID());
        assertTrue(delete);
        System.out.println("Deleted " + delete);
    }

    @Test
    @Order(4)
    void getAll() {
        System.out.println("Show All");
        System.out.println(addressService.getAll());
    }
}