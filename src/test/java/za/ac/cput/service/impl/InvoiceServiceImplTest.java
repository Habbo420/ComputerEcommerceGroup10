package za.ac.cput.service.impl;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.*;
import za.ac.cput.factory.*;

import static org.junit.jupiter.api.Assertions.*;

/*
    InvoiceServiceImplTest.java
    Author: Alexander Draai - 220118744
    This is the test class InvoiceServiceImpl
    Date: 10 - 06 - 2023
 */
@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InvoiceServiceImplTest {

    @Autowired
    private InvoiceServiceImpl service;

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
            "Evetech",
            homeAddress,
            "021 445 9912",
            "techEve@gmail.com"
    );

    private static final Invoice invoice = InvoiceFactory.buildInvoice(
            storeDetails,
            sales
    );


    @Test
    void a_create(){
        Invoice invoiceCreated = service.create(invoice);
        assertEquals(invoice.getInvoiceNumber(), invoiceCreated.getInvoiceNumber());
        System.out.println("Created Invoice: " + invoiceCreated);
    }
    @Test
    void b_read(){
        Invoice invoiceRead = service.read(invoice.getInvoiceNumber());
        assertNotNull(invoiceRead);
        System.out.println("Read " + invoiceRead);
    }
    @Test
    @Disabled
    void c_update(){
        Invoice updatedInvoice = new Invoice.Builder().copy(invoice)
                .build();
        assertNotNull(service.update(updatedInvoice));
        System.out.println("Updated -> " + updatedInvoice);
    }
    @Test
    @Disabled
    void d_delete(){
        boolean success = service.delete(invoice.getInvoiceNumber());
        assertTrue(success);
        System.out.println("Deleted -> " + success);
    }
    @Test
    void getAll(){
        System.out.println("Get All:");
        System.out.println(service.getAll());
    }

}