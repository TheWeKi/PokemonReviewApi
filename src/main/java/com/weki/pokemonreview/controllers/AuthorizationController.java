package com.weki.pokemonreview.controllers;

import com.weki.pokemonreview.DtoModels.LoginDto;
import com.weki.pokemonreview.DtoModels.RegisterDto;
import com.weki.pokemonreview.models.Role;
import com.weki.pokemonreview.models.UserEntity;
import com.weki.pokemonreview.repositories.RoleRepository;
import com.weki.pokemonreview.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/auth")
public class AuthorizationController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthorizationController(
            AuthenticationManager authenticationManager,
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {

        if( userRepository.existsByUsername(registerDto.getUsername()) ) {
            return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
        }

        UserEntity user = new UserEntity();
        user.setUsername( registerDto.getUsername() );
        user.setPassword( passwordEncoder.encode( registerDto.getPassword() ) );

        Role role = roleRepository.findByRole("USER").get();

        user.setRoles( Collections.singletonList(role) );

        userRepository.save(user);

        return new ResponseEntity<>("User created successfully", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginDto.getUsername(), loginDto.getPassword()
                        )
        );

        SecurityContextHolder.getContext().setAuthentication( authentication );

        return new ResponseEntity<>("Login Successful", HttpStatus.OK);
    }
}
