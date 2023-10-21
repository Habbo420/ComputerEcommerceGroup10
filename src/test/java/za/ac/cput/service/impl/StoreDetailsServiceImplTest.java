package za.ac.cput.service.impl;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.City;
import za.ac.cput.domain.Country;
import za.ac.cput.domain.StoreDetails;
import za.ac.cput.factory.AddressFactory;
import za.ac.cput.factory.CityFactory;
import za.ac.cput.factory.CountryFactory;
import za.ac.cput.factory.StoreDetailsFactory;

import static org.junit.jupiter.api.Assertions.*;

/*
    StoreDetailsServiceImplTest.java
    Author: Hanno Visser Immelman 221074414
    This is the test class StoreDetailsServiceImpl
    Date: 10 - 06 - 2023
*/

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StoreDetailsServiceImplTest{
    @Autowired
    private StoreDetailsServiceImpl service;

    private static final Address homeAddress = AddressFactory.buildTestAddress(
            5L
    );

    private static final StoreDetails storeDetails = StoreDetailsFactory.buildStoreDetails(
            "ExtraTecha",
            homeAddress,
            "066 224 9965",
            "ExtraTecha@gmail.com"
    );

    @Test
    @Order(1)
    void create(){
        StoreDetails created = service.create(storeDetails);
        assertEquals(storeDetails.getStoreID(), created.getStoreID());
        System.out.println(created);
    }

    @Test
    @Order(2)
    void read(){
        StoreDetails read = service.read(storeDetails.getStoreID());
        assertNotNull(read);
        System.out.println("Read: " + read);
    }


    @Test
    @Order(3)
    void update(){
        StoreDetails updated = new StoreDetails.Builder().copy(storeDetails)
                .setStoreName("N Computers")
                .build();
        assertNotNull(service.update(updated));
        System.out.println("Updated: "+updated);
    }

    @Test
    @Order(4)
    @Disabled
    void delete(){
        boolean success = service.delete(storeDetails.getStoreID());
        assertTrue(success);
        System.out.println("Deleted: " + success);
    }

    @Test
    @Order(5)
    void getAll(){
        System.out.println("Show all: "+service.getAll());
    }
}
