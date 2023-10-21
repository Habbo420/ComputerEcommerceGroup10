package za.ac.cput.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import za.ac.cput.domain.Product;

import java.io.Serializable;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewDto implements Serializable {
    Long reviewID;
    int rating;
    UserDto customer;
    Product product;
}
