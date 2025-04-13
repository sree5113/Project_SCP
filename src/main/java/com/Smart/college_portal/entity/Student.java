package com.Smart.college_portal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long grade;

    private Long addmission_no;

    @Column(name = "gaurdian/parent")
    private String parent;

    private Long mobile;

    private LocalDate joinDate;


    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user; // link to login
}
