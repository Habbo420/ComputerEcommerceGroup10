package za.ac.cput.service.impl;

/* SalesServiceImplTest.java
Test for service implementation for Sales
Author: David Henriques Garrancho (221475982)
Date: 16 August 2023
*/

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.*;
import za.ac.cput.factory.*;
import za.ac.cput.repository.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest
class SalesServiceImplTest {

    @Autowired
    private SalesServiceImpl service;
    @Autowired
    private SalesRepository repository;

    private static final User customer = UserFactory.buildTestCustomer(
        2L
    );


    private static final Sales sales = SalesFactory.buildSales(
            "16-08-2023",
            4560.00,
            customer
    );


    @Test
    //@Transactional
    void a_create() {
        Sales created = service.create(sales);
        System.out.println("Created " + created);
    }

    @Test
    //@Transactional
    void b_read() {
        Sales read = service.read(sales.getSaleID());
        System.out.println("Read: "+ read);
        assertNotNull(read);
    }

    @Test
    //@Transactional
    void c_update() {
        Sales updated = new Sales.Builder().copy(sales).setSaleDate("15-09-2023").setTotalAmount(3520.00).build();
        repository.save(updated);
        assertNotNull(updated);
        System.out.println("Updated: " + updated);
    }

    @Test
    @Disabled
    void delete() {
    }

    @Test
    //@Transactional
    void d_getAll() {
        System.out.println("Show All:");
        System.out.println(service.getAll());
    }
}