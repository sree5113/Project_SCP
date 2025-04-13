package com.Smart.college_portal.repository;

import com.Smart.college_portal.entity.Student;
import com.Smart.college_portal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByUser(User user);
}
