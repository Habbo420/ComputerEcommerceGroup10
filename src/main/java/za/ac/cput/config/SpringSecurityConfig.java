package za.ac.cput.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import za.ac.cput.jwt.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final MyUserDetailsService myUserDetailsService ;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeRequests(authorizeRequests -> {
                    authorizeRequests
                            .requestMatchers("/user/register", "/user/authenticate", "/post/search", "/storeDetails/getAll",
                                    "/city/getAll", "/country/getAll", "/address/getAll", "/city/read", "/country/read", "/address/read",
                                    "/storeDetails/read").permitAll()
                            //, "user/**" add this if users becomes janky
                            .requestMatchers("sales/create", "review/create", "enquiry/create").hasAuthority("CUSTOMER")
                            .requestMatchers("product/create", "address/create").hasAuthority("EMPLOYEE")

                            .anyRequest().authenticated();
                })
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception
    { return authenticationConfiguration.getAuthenticationManager();}

    @Bean
    public PasswordEncoder passwordEncoder()
    { return new BCryptPasswordEncoder(); }

}