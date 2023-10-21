package za.ac.cput.service.impl;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.*;
import za.ac.cput.factory.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReviewServiceImplTest {

    @Autowired
    private ReviewServiceImpl service;

    private static final Product product = ProductFactory.buildProduct("FX 950", "Item", "Next Generation gaming with the RTX 950", 4000.00, 3500.00,true);
    private static final User customer = UserFactory.buildCustomer(
            "Luke",
            "Ben",
            "LW@gmail.com",
            "wufh%2465"
    );

    private static final Review review = ReviewFactory.buildReview( 7, product, customer);

    @Test
    void a_create() {
        Review created = service.create(review);
        System.out.println("Created " + created);
    }

    @Test
    void b_read() {
        //assert review != null;
        Review read = service.read(review.getReviewID());
        assertNotNull(read);
        System.out.println("Read: "+ read);
    }

    @Test
    void c_update() {
        Review updated = new Review.Builder().copy(review).setRating(8).build();
        assertNotNull(updated);
        System.out.println("Updated: " + updated);
    }

    @Test
    @Disabled
    void d_delete() {
        boolean success = service.delete(review.getReviewID());
        assertTrue(success);
        System.out.println("Deleted: " + success);
    }

    @Test
    void e_getAll() {
        System.out.println("Show All:");
        System.out.println(service.getAll());
    }
}
