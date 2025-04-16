package com.Smart.college_portal.service;

import com.Smart.college_portal.dto.GradeRequestDTO;
import com.Smart.college_portal.dto.GradeResponseDTO;
import com.Smart.college_portal.entity.*;
import com.Smart.college_portal.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;
    private final UserRepository userRepository;

    @Override
    public GradeResponseDTO addGrade(GradeRequestDTO dto) {
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        String currentEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(currentEmail)
                .orElseThrow(() -> new RuntimeException("Faculty not found"));
        Faculty faculty = facultyRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Faculty profile not found"));

        Grade grade = Grade.builder()
                .subject(dto.getSubject())
                .grade(dto.getGrade())
                .student(student)
                .faculty(faculty)
                .build();

        gradeRepository.save(grade);

        return new GradeResponseDTO(
                grade.getId(),
                grade.getSubject(),
                grade.getGrade(),
                student.getName(),
                faculty.getName()
        );
    }

    @Override
    public List<GradeResponseDTO> getGradesForStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return gradeRepository.findByStudent(student)
                .stream()
                .map(g -> new GradeResponseDTO(
                        g.getId(),
                        g.getSubject(),
                        g.getGrade(),
                        g.getStudent().getName(),
                        g.getFaculty().getName()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<GradeResponseDTO> getMyGrades() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Student student = studentRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Student profile not found"));

        return getGradesForStudent(student.getId());
    }
}
