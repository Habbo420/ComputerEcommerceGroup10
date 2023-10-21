package za.ac.cput.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.domain.Product;
import za.ac.cput.factory.ProductFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

    private static final Product product = ProductFactory.buildProduct("RTX 3060 TI", "Item", "Next Generation gaming with the RTX 4050", 4800.00, 4200.00, true);

    @Autowired
    private TestRestTemplate restTemplate;

    private final String baseURL = "http://localhost:8080/product";

    @Test
    void a_create() {
        List<Product> products = new ArrayList<>();

        // Create multiple Product objects
        Product product1 = ProductFactory.buildProduct("RTX 3060 TI", "Item 1", "Next Generation gaming with the RTX 4050", 4800.00, 4200.00, true);
        Product product2 = ProductFactory.buildProduct("GTX 1660", "Item 2", "High-performance gaming GPU", 3200.00, 2800.00, true);
        Product product3 = ProductFactory.buildProduct("RX 6700 XT", "Item 3", "AMD's flagship gaming GPU", 5500.00, 4900.00, true);

        // Add products to the list
        products.add(product1);
        products.add(product2);
        products.add(product3);

        for (Product product : products) {
            ResponseEntity<Product> postResponse = restTemplate.postForEntity(baseURL + "/create", product, Product.class);
            assertNotNull(postResponse);
            assertNotNull(postResponse.getBody());
            assertEquals(HttpStatus.OK, postResponse.getStatusCode());

            Product savedProduct = postResponse.getBody();
            System.out.println("Saved data: " + savedProduct);
            assertEquals(savedProduct.getProductID(), postResponse.getBody().getProductID());
        }
    }

    @Test
    void b_read() {
            String url = baseURL + "/read/" + product.getProductID();
            System.out.println("URL: " + url);
            ResponseEntity<Product> response = restTemplate.getForEntity(url, Product.class);
            assertEquals(product.getProductID(), response.getBody().getProductID());
            System.out.println(response.getBody());
    }
    @Test
    void c_update() {
            Product updated = new Product.Builder().copy(product).setStock(false).build();
            String url = baseURL + "/update";
            System.out.println("URL: " + url);
            System.out.println("Post data: " + updated);
            ResponseEntity<Product> response = restTemplate.postForEntity(url, updated, Product.class);
            assertNotNull(response.getBody());
    }

    @Test
    @Disabled
    void e_delete() {
        String url = baseURL + "/delete/" + product.getProductID();
        System.out.println("URL: " + url);
        restTemplate.delete(url);

    }

    @Test
    void d_getAll() {
            String url = baseURL + "/getAll";
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> entity = new HttpEntity<>(null, headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            System.out.println("Show ALL:");
            System.out.println(response);
            System.out.println(response.getBody());
        }
}
