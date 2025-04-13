package com.Smart.college_portal.security;

import com.Smart.college_portal.repository.AdminRepository;
import com.Smart.college_portal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // First check if it's an Admin
        return adminRepository.findByEmail(email)
                .map(admin -> (UserDetails) admin)
                .orElseGet(() -> userRepository.findByEmail(email)
                        .map(user -> org.springframework.security.core.userdetails.User.builder()
                                .username(user.getEmail())
                                .password(user.getPassword())
                                .roles(user.getRole().name()) // auto adds ROLE_ prefix
                                .build())
                        .orElseThrow(() -> new UsernameNotFoundException("User/Admin not found")));
    }

}
