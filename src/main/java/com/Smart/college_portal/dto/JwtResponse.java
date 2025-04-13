package com.Smart.college_portal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {

    private String token;
    private String role;
}
