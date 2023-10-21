package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PasswordCheckController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/checkPassword")
    public String checkPassword(@RequestParam String password) {
        // Encode the provided password
        String encodedPassword = passwordEncoder.encode(password);

        // Check if the provided password matches the encoded password
        boolean isMatch = passwordEncoder.matches(password, encodedPassword);

        return "Password matches: " + isMatch;
    }
}
