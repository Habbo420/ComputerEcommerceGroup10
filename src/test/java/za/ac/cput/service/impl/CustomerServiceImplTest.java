package za.ac.cput.service.impl;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.User;
import za.ac.cput.factory.UserFactory;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerServiceImplTest {

    @Autowired
    private UserServiceImpl service;

    private static final User customer = UserFactory.buildCustomer(
            "Damien",
            "Garrancho",
            "DavidGary@gmail.com",
            "Gary!s23"
    );

    @Test
    @Order(1)
    void create() {
        User created = service.create(customer);
        System.out.println("Created " + created);
    }

    @Test
    @Order(2)
    void read() {
        User read = service.read(customer.getCustomerID());
        assertNotNull(read);
        System.out.println("Read: "+ read);
    }

    @Test
    @Order(3)
    void update() {
        User updated = new User.Builder().copy(customer).setLastName("Gary").build();
        assertNotNull(updated);
        System.out.println("Updated: " + updated);
    }

    @Test
    void delete() {
        boolean success = service.delete(customer.getCustomerID());
        assertTrue(success);
        System.out.println("Deleted -> " + success);
    }

    @Test
    @Order(4)
    void d_getAll() {
        System.out.println("Show All:");
        System.out.println(service.getAll());
    }
}