package com.weki.pokemonreview.security;

import com.weki.pokemonreview.models.Role;
import com.weki.pokemonreview.models.UserEntity;
import com.weki.pokemonreview.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(userRepository.existsByUsername(username)) {
            throw new UsernameNotFoundException("User Id not found");
        }
        UserEntity user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("No User found")
        );
        return new User(
                user.getUsername(),
                user.getPassword(),
                mapToAuthority( user.getRoles() )
        );
    }

    private List<GrantedAuthority> mapToAuthority(List<Role> roles) {
        return
                roles.stream().map(
                        role -> new SimpleGrantedAuthority(role.getRole())
                ).collect(Collectors.toList());
    }
}
