package com.esdo.bepilot.Model.Request;

import lombok.Data;

@Data
public class AccountInputDTO {
    private String email;
    private String password;
    private String role;
}

