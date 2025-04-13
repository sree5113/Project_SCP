package com.Smart.college_portal.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminRegisterRequest {
    private String name;
    private String email;
    private String password;
}
