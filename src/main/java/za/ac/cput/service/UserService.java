package za.ac.cput.service;

import org.springframework.http.ResponseEntity;
import za.ac.cput.dto.LoginDto;
import za.ac.cput.dto.RegisterDto;
import za.ac.cput.domain.User;
import za.ac.cput.domain.Role;
import za.ac.cput.dto.UpdateUserDto;

import java.util.List;

/*
    CustomerService.java
    Author: David Henriques Garrancho
    This is the Service for the service the Customer entity
    Date: 09 - 06 - 2023
 */

public interface UserService extends IService<User, Long> {

    User updateUser(UpdateUserDto updateUserDto);

    List<User> getAll();

    User saverUser(User user);

    String authenticate(LoginDto loginDto);

    ResponseEntity<?> register (RegisterDto registerDto);

    User findByEmail(String email);

    Role saveRole(Role role);
}
