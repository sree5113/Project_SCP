//package com.Smart.college_portal.config;
//
//import com.Smart.college_portal.entity.User;
//import com.Smart.college_portal.repository.UserRepository;
//import com.Smart.college_portal.util.Role;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class AdminInitializer implements CommandLineRunner {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        if(!userRepository.existsByEmail("admin@college.com")){
//            User admin = User.builder()
//
//                    .name("Default Admin")
//                    .email("admin@college.com")
//                    .password(passwordEncoder.encode("admin123"))
//                    .role(Role.ADMIN)
//                    .build();
//            userRepository.save(admin);
//            System.out.println("Default Admin created: admin@college.com / admin123");
//        }
//    }
//}
