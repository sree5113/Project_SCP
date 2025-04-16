package com.Smart.college_portal.repository;

import com.Smart.college_portal.entity.Attendance;
import com.Smart.college_portal.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByStudent(Student student);
    List<Attendance> findByDateAndSubject(LocalDate date, String subject);
}
