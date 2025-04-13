package com.Smart.college_portal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String department;
    private String subject;
    private String phone;
    private LocalDate joinDate;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user; // link to login
}
