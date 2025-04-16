package com.Smart.college_portal.service;

import com.Smart.college_portal.dto.AttendanceRequestDTO;
import com.Smart.college_portal.dto.AttendanceResponseDTO;
import com.Smart.college_portal.entity.Attendance;
import com.Smart.college_portal.entity.Faculty;
import com.Smart.college_portal.entity.Student;
import com.Smart.college_portal.entity.User;
import com.Smart.college_portal.repository.AttendanceRepository;
import com.Smart.college_portal.repository.FacultyRepository;
import com.Smart.college_portal.repository.StudentRepository;
import com.Smart.college_portal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService{


    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;
    private final UserRepository userRepository;


    @Override
    public AttendanceResponseDTO addAttendance(AttendanceRequestDTO dto) {
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(()-> new RuntimeException("Student not found"));

        String currentEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(currentEmail)
                .orElseThrow(()-> new RuntimeException("Faculty not found"));
        Faculty faculty = facultyRepository.findByUser(user)
                .orElseThrow(()->new RuntimeException("Faculty not found"));

        Attendance attendance = Attendance.builder()
                .student(student)
                .subject(dto.getSubject())
                .present(dto.isPresent())
                .date(dto.getDate())
                .build();
        attendanceRepository.save(attendance);

                return new AttendanceResponseDTO(
                        attendance.getId(),
                        student.getName(),
                        attendance.getSubject(),
                        attendance.isPresent(),
                        attendance.getDate()

                );
    }

    @Override
    public List<AttendanceResponseDTO> getAttendanceForStudents(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return attendanceRepository.findByStudent(student)
                .stream()
                .map(a -> new AttendanceResponseDTO(
                        a.getId(),
                        a.getStudent().getName(),
                        a.getSubject(),
                        a.isPresent(),
                        a.getDate()
                )).collect(Collectors.toList());
    }

    @Override
    public List<AttendanceResponseDTO> getMyAttendance() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Student student = studentRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Student profile not found"));

        return getAttendanceForStudents(student.getId());
    }
}
