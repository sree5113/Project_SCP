package com.Smart.college_portal.repository;

import com.Smart.college_portal.entity.Faculty;
import com.Smart.college_portal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Optional<Faculty> findByUser(User user);
}
