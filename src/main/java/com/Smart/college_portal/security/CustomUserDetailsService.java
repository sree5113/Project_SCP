package com.Smart.college_portal.security;

import com.Smart.college_portal.repository.AdminRepository;
import com.Smart.college_portal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {


        Optional<UserDetails> adminUserDetails = adminRepository.findByEmail(email)
                .map(admin -> (UserDetails) admin);

        if (adminUserDetails.isPresent()) {
            return adminUserDetails.get();
        }


        return userRepository.findByEmail(email)
                .map(user -> {

                    user.setLastLogin(LocalDateTime.now());
                    userRepository.save(user);


                    return org.springframework.security.core.userdetails.User.builder()
                            .username(user.getEmail())
                            .password(user.getPassword())
                            .authorities(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
                            .roles(user.getRole().name())
                            .build();
                })
                .orElseThrow(() -> new UsernameNotFoundException("User/Admin not found with email: " + email));
    }
}
