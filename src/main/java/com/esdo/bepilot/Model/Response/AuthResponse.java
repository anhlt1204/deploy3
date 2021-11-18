package com.esdo.bepilot.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String name;
    private String avatar;
    private String email;
    private String role;
    private String token;
}
