package com.Smart.college_portal.service;

import com.Smart.college_portal.dto.FacultyRequestDTO;
import com.Smart.college_portal.dto.FacultyResponseDTO;
import com.Smart.college_portal.entity.Faculty;
import com.Smart.college_portal.entity.User;
import com.Smart.college_portal.repository.FacultyRepository;
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
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUserUtil currentUserUtil;


    @Override
    public FacultyResponseDTO createFaculty(FacultyRequestDTO dto) {

        //user account

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.FACULTY);
        userRepository.save(user);

        //faculty

        Faculty faculty = new Faculty();
        faculty.setName(dto.getName());
        faculty.setDepartment(dto.getDepartment());
        faculty.setSubject(dto.getSubject());
        faculty.setPhone(dto.getPhone());
        faculty.setJoinDate(dto.getJoinDate());
        faculty.setUser(user);

        facultyRepository.save(faculty);

        return new FacultyResponseDTO(
                faculty.getId(),
                faculty.getName(),
                user.getEmail(),
                faculty.getDepartment(),
                faculty.getSubject(),
                faculty.getPhone(),
                faculty.getJoinDate()
        );

    }

    @Override
    public List<FacultyResponseDTO> getAllFaculty() {
        return  facultyRepository.findAll().stream()
                .map(f -> new FacultyResponseDTO(
                        f.getId(),
                        f.getName(),
                        f.getUser().getEmail(),
                        f.getDepartment(),
                        f.getSubject(),
                        f.getPhone(),
                        f.getJoinDate()))
                .collect(Collectors.toList());
    }


    @Override
    public FacultyResponseDTO getOwnProfile() {
        String email = CurrentUserUtil.getCurrentUserEmail();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Faculty faculty = facultyRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Faculty profile not found"));

        return new FacultyResponseDTO(
                faculty.getId(), faculty.getName(), user.getEmail(),
                faculty.getDepartment(), faculty.getSubject(), faculty.getPhone(), faculty.getJoinDate()
        );
    }

    @Override
    public FacultyResponseDTO updateOwnProfile(FacultyRequestDTO dto) {
        String email = CurrentUserUtil.getCurrentUserEmail();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Faculty faculty = facultyRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Faculty not found"));


        faculty.setName(dto.getName());
        user.setEmail(dto.getEmail());
        faculty.setDepartment(dto.getDepartment());
        faculty.setSubject(dto.getSubject());
        faculty.setPhone(dto.getPhone());
        facultyRepository.save(faculty);

        return new FacultyResponseDTO(
                faculty.getId(), faculty.getName(), user.getEmail(),
                faculty.getDepartment(), faculty.getSubject(), faculty.getPhone(), faculty.getJoinDate()
        );
    }

}
