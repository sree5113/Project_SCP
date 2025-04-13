package com.Smart.college_portal.controller;


import com.Smart.college_portal.dto.StudentRequestDTO;
import com.Smart.college_portal.dto.StudentResponseDTO;
import com.Smart.college_portal.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class StudentController {

    private final StudentService studentService;


    @PostMapping
    public ResponseEntity<StudentResponseDTO> createStudent(@RequestBody StudentRequestDTO dto){
        return ResponseEntity.ok(studentService.createStudent(dto));
    }


    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudent());
    }


}
