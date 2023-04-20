package edu.javeriana.ProyectoDesarrolloWeb.Security.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http){
        try {
            http.cors().and().csrf().disable()
                    .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .authorizeHttpRequests().requestMatchers(HttpMethod.POST, "/api/login").permitAll()
                    .anyRequest().authenticated();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            return http.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
