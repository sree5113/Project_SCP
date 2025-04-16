package com.Smart.college_portal.service;

import com.Smart.college_portal.dto.AdminDashBoardDTO;
import com.Smart.college_portal.repository.AttendanceRepository;
import com.Smart.college_portal.repository.FacultyRepository;
import com.Smart.college_portal.repository.StudentRepository;
import com.Smart.college_portal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AdminDashboardService {

    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;
    private final UserRepository userRepository;


    public AdminDashBoardDTO getDashboardData(){
        AdminDashBoardDTO dashBoard = new AdminDashBoardDTO();

        dashBoard.setTotalStudents(studentRepository.count());

        dashBoard.setTotalFaculty(facultyRepository.count());

        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
        dashBoard.setRecentLogins(userRepository.countByLastLoginAfter(yesterday));

        return dashBoard;
    }


}
