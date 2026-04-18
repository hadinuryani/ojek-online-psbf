package com.ojek.online.config;

import com.ojek.online.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler roleBasedSuccessHandler() {
        return (request, response, authentication) -> {
            var authorities = authentication.getAuthorities();
            String redirectUrl = "/customer/dashboard";

            for (var authority : authorities) {
                String role = authority.getAuthority();
                if (role.equals("ROLE_ADMIN")) {
                    redirectUrl = "/admin/dashboard";
                    break;
                } else if (role.equals("ROLE_DRIVER")) {
                    redirectUrl = "/driver/dashboard";
                    break;
                }
            }

            response.sendRedirect(redirectUrl);
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/css/**", "/js/**", "/images/**", "/webjars/**").permitAll()
                .requestMatchers("/", "/login", "/register").permitAll()
                // Admin only
                .requestMatchers("/admin/**", "/users/**").hasRole("ADMIN")
                // Driver pages
                .requestMatchers("/driver/**").hasAnyRole("DRIVER", "ADMIN")
                // Customer pages
                .requestMatchers("/customer/**").hasAnyRole("CUSTOMER", "ADMIN")
                // Shared admin management pages (CRUD)
                .requestMatchers("/drivers/**", "/customers/**", "/vehicles/**",
                        "/locations/**", "/promos/**").hasRole("ADMIN")
                // Notifications: admin manages all, driver/customer see own
                .requestMatchers("/notifications/**").hasRole("ADMIN")
                // Ratings: admin manages all
                .requestMatchers("/ratings/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler(roleBasedSuccessHandler())
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .permitAll()
            )
            .exceptionHandling(ex -> ex
                .accessDeniedPage("/access-denied")
            )
            .userDetailsService(userDetailsService);

        return http.build();
    }
}
