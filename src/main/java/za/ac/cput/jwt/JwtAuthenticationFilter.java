package za.ac.cput.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import za.ac.cput.config.MyUserDetailsService;

import java.io.IOException;


@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final MyUserDetailsService myUserDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        String token = jwtService.getToken(request);

        if (token != null) {
            System.out.println("Received token: " + token);

            if (jwtService.validateToken(token)) {
                String email = jwtService.extractUsername(token);

                UserDetails userDetails = myUserDetailsService.loadUserByUsername(email);
                if (userDetails != null) {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    System.out.println("User details not found for email: " + email);
                }
            } else {
                System.out.println("Token validation failed for token: " + token);
            }
        }

        filterChain.doFilter(request, response);
    }
}