package za.ac.cput.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SalesDto implements Serializable {

    Long saleID;
    private String saleDate;
    private Double totalAmount;
    UserDto customer;
}