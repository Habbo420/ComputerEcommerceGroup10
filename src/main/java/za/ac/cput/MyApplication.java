package za.ac.cput;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;
import za.ac.cput.domain.User;
import za.ac.cput.domain.Role;
import za.ac.cput.domain.RoleName;
import za.ac.cput.repository.RoleRepository;
import za.ac.cput.repository.UserRepository;
import za.ac.cput.service.UserService;
import za.ac.cput.service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@SpringBootApplication
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserServiceImpl iUserService, RoleRepository iRoleRepository, UserRepository iUserRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            List<RoleName> roleNames = Arrays.asList(RoleName.CUSTOMER, RoleName.ADMIN, RoleName.EMPLOYEE);

            for (RoleName roleName : roleNames) {
                Role role = iRoleRepository.findByRoleName(roleName);

                if (role == null) {
                    iUserService.saveRole(new Role(roleName));

                    String email = roleName.toString().toLowerCase() + "@gmail.com";
                    String password = roleName.toString().toLowerCase() + "Password";

                    iUserService.saverUser(new User(email, passwordEncoder.encode(password), new ArrayList<>()));

                    role = iRoleRepository.findByRoleName(roleName);
                    User user = (User) iUserRepository.findByEmail(email).orElse(null);

                    if (user != null) {
                        user.getRole().add(role);
                        iUserService.saverUser(user);
                    }
                }
            }
        };
    }




    /*@Bean
    CommandLineRunner run(UserService iUserService, RoleRepository iRoleRepository, UserRepository iUserRepository, PasswordEncoder passwordEncoder) {
        return args ->
        {
            Role guestRole = iRoleRepository.findByRoleName(RoleName.CUSTOMER);
            if (guestRole == null) {
                iRoleRepository.save(new Role(RoleName.CUSTOMER));
                Role role = iRoleRepository.findByRoleName(RoleName.CUSTOMER);
                User user = (User) iUserRepository.findByEmail("customer@gmail.com").orElse(null);
                user.getRole().add(role);
                iUserService.create(user);
            }

            Role userRole = iRoleRepository.findByRoleName(RoleName.EMPLOYEE);
            if (userRole == null) {
                iRoleRepository.save(new Role(RoleName.EMPLOYEE));
                Role role = iRoleRepository.findByRoleName(RoleName.EMPLOYEE);
                User user = (User) iUserRepository.findByEmail("employee@extraTecha.com").orElse(null);
                user.getRole().add(role);
                iUserService.create(user);

            }

            Role supportRole = iRoleRepository.findByRoleName(RoleName.ADMIN);
            if (supportRole == null) {
                iRoleRepository.save(new Role(RoleName.ADMIN));
                Role role = iRoleRepository.findByRoleName(RoleName.ADMIN);
                User user = (User) iUserRepository.findByEmail("admin@admin.com").orElse(null);
                user.getRole().add(role);
                iUserService.create(user);

            }
        };
    }*/

}
