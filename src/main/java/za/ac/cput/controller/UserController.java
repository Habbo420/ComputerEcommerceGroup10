package za.ac.cput.controller;

/* CustomerController.java
Controller for Customer
Author: David Henriques Garrancho (221475982)
Date: 17 June 2023
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Role;
import za.ac.cput.dto.LoginDto;
import za.ac.cput.dto.RegisterDto;
import za.ac.cput.domain.User;
import za.ac.cput.dto.UpdateUserDto;
import za.ac.cput.dto.UserDto;
import za.ac.cput.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public User create(@RequestBody User customer) {
        return userService.create(customer);
    }

    @GetMapping("/read/{id}")
    public User read(@PathVariable Long id) {
        return userService.read(id);
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody UpdateUserDto updateUserDto) {
        return userService.updateUser(updateUserDto);
    }

    @DeleteMapping("/delete/{userID}")
    public ResponseEntity<?> delete(@PathVariable Long userID) {
        boolean deleted = userService.delete(userID);
        if (deleted) {
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAll")
    public List<User> getAll() {
        return userService.getAll();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register (@RequestBody RegisterDto registerDto)
    { return  userService.register(registerDto);}

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody LoginDto loginDto)
    { return  userService.authenticate(loginDto);}

    @GetMapping("/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        User user = userService.findByEmail(email);

        if (user != null) {
            UserDto userDto = mapUserToUserDto(user);
            return ResponseEntity.ok(userDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private UserDto mapUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setCustomerID(user.getCustomerID());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());

        List<String> roleNames = user.getRole().stream()
                .map(Role::getRoleName)
                .collect(Collectors.toList());

        userDto.setRoleNames(roleNames);

        return userDto;
    }

}
