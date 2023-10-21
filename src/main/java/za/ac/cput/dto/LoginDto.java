package za.ac.cput.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginDto implements Serializable {

    //it's a Data Trasfer Object for Login
    String email ;
    String password ;
}
