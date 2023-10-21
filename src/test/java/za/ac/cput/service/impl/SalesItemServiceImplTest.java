package za.ac.cput.service.impl;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.Sales;
import za.ac.cput.domain.SalesItem;
import za.ac.cput.factory.ProductFactory;
import za.ac.cput.factory.SalesFactory;
import za.ac.cput.factory.SalesItemFactory;
import za.ac.cput.repository.SalesItemRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SalesItemServiceImplTest {

    @Autowired
    private SalesItemServiceImpl service;

    @Autowired
    private SalesItemRepository repository;

    private static final List<Product> products = Arrays.asList(
            ProductFactory.buildTestProduct(1L),
            ProductFactory.buildTestProduct(3L)
    );

    private static final Sales sales = SalesFactory.buildTestSales(
            5L
    );

    private static final SalesItem salesItem = SalesItemFactory.buildSales(sales, products, products.size());


    @Test
    @Transactional
    @Order(1)
    void create() {
        SalesItem created = service.create(salesItem);
        System.out.println("Created " + created);
    }


    @Test
    @Order(2)
    void read() {
        SalesItem read = service.read(salesItem.getSalesItemID());
        assertNotNull(read);
        System.out.println("Read: "+ read);
    }

    @Test
    @Order(3)
    void update() {
        SalesItem updated = new SalesItem.Builder().copy(salesItem).build();
        repository.save(updated);
        assertNotNull(updated);
        System.out.println("Updated: " + updated);
    }

    @Test
    @Disabled
    void delete() {
        boolean success = service.delete(salesItem.getSalesItemID());
        assertTrue(success);
        System.out.println("Deleted: " + success);
    }

    @Test
    @Order(4)
    void getAll() {
        System.out.println("Show All:");
        System.out.println(service.getAll());
    }
}
