package za.ac.cput.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserDto implements Serializable {
     Long customerID;
     String firstName;
     String lastName;
     String email;
     String password;
     List<String> roleNames;
}
