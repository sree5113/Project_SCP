package com.Smart.college_portal.repository;

import com.Smart.college_portal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    long countByLastLoginAfter(LocalDateTime dateTime);

}
