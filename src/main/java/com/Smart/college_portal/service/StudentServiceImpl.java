package com.Smart.college_portal.service;


import com.Smart.college_portal.dto.FacultyRequestDTO;
import com.Smart.college_portal.dto.FacultyResponseDTO;
import com.Smart.college_portal.dto.StudentRequestDTO;
import com.Smart.college_portal.dto.StudentResponseDTO;
import com.Smart.college_portal.entity.Student;
import com.Smart.college_portal.entity.User;
import com.Smart.college_portal.repository.StudentRepository;
import com.Smart.college_portal.repository.UserRepository;
import com.Smart.college_portal.util.CurrentUserUtil;
import com.Smart.college_portal.util.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public StudentResponseDTO createStudent(StudentRequestDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.STUDENT);
        userRepository.save(user);


        Student student = new Student();
        student.setName(dto.getName());
        student.setGrade(dto.getGrade());
        student.setAddmission_no(dto.getAddmission_no());
        student.setParent(dto.getParent());
        student.setMobile(dto.getMobile());
        student.setJoinDate(dto.getJoinDate());
        student.setUser(user);
        studentRepository.save(student);

        return new StudentResponseDTO(
                student.getId(),
                student.getName(),
                user.getEmail(),
                student.getGrade(),
                student.getAddmission_no(),
                student.getParent(),
                student.getMobile(),
                student.getJoinDate()

        );
    }

    @Override
    public List<StudentResponseDTO> getAllStudent() {
        return studentRepository.findAll().stream()
                .map(s -> new StudentResponseDTO(
                        s.getId(),
                        s.getName(),
                        s.getUser().getEmail(),
                        s.getGrade(),
                        s.getAddmission_no(),
                        s.getParent(),
                        s.getMobile(),
                        s.getJoinDate()))
                .collect(Collectors.toList());
    }

    @Override
    public StudentResponseDTO getOwnProfile() {
        String email = CurrentUserUtil.getCurrentUserEmail();
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("User not found"));
        Student student = studentRepository.findByUser(user)
                .orElseThrow(()-> new RuntimeException("Student not found"));

        return new StudentResponseDTO(
                student.getId(), student.getName(), user.getEmail(), student.getGrade(), student.getAddmission_no(),
                student.getParent(), student.getMobile(), student.getJoinDate()
        );
    }
}
