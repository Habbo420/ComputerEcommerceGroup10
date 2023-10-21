package za.ac.cput.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UpdateUserDto implements Serializable {
    String firstName;
    String lastName;
    String email;
}