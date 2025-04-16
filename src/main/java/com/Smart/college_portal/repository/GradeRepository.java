package com.Smart.college_portal.repository;

import com.Smart.college_portal.entity.Grade;
import com.Smart.college_portal.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByStudent(Student student);
}
