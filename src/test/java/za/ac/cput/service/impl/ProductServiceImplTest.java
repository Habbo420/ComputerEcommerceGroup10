package za.ac.cput.service.impl;
/* ProductServiceImpl.java
 This is the test class ProductService
 Author: Reece Bergstedt - 221075240
 Date: 10 June 2023
*/
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Product;
import za.ac.cput.factory.ProductFactory;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl service;


    private static final List<Product> products = Arrays.asList(
            ProductFactory.buildProduct("RTX 3060 TI", "Item", "Next Generation gaming with the RTX 4050", 4800.00, 4000.00 ,true));

    @Test
    void a_create() {

        for (Product product : products) {
            Product created = service.create(product);
            System.out.println("Create: " + created);
        }
    }

    @Test
    void b_read() {

        for (Product product : products) {
            Product read = service.read(product.getProductID());
            assertNotNull(read);
            System.out.println("Read: " + read);
        }

    }

    @Test
    void c_update() {

        for (Product product : products) {
            Product updated = new Product.Builder()
                    .copy(product)
                    .setProductPrice(6000)
                    .build();

            assertNotNull(updated);

            // Perform the update operation here, for example:
            Product updatedProduct = service.update(updated);

            System.out.println("Updated: " + updatedProduct);
        }
    }

    @Test
    @Disabled
    void delete() {

    }

    @Test
    void d_getAll() {
        System.out.println("Show all: ");
        System.out.println(service.getAll());
    }
}
