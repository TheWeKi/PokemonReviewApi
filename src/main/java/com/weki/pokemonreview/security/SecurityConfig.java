package com.weki.pokemonreview.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests().anyRequest().authenticated()
                .and()
                .sessionManagement( s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS) )
                .csrf().disable()
                .httpBasic();

        return http.build();
    }

    @Bean
    public UserDetailsManager userDetails() {
        UserDetails admin = User.builder().username("admin").password("password")
                .passwordEncoder(pass -> passwordEncoder().encode(pass))
                .roles("ADMIN").build();
        UserDetails user = User.builder().username("user").password("password")
                .passwordEncoder(pass -> passwordEncoder().encode(pass))
                .roles("USER").build();
        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
