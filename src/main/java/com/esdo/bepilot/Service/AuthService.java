package com.esdo.bepilot.Service;

import com.esdo.bepilot.Model.Request.AuthRequest;
import com.esdo.bepilot.Model.Request.CheckOTPRequest;
import com.esdo.bepilot.Model.Request.ForgotPasswordRequest;
import com.esdo.bepilot.Model.Request.ResetPasswordRequest;
import com.esdo.bepilot.Model.Response.AuthResponse;
import com.esdo.bepilot.Model.Response.EmployeeResponse;
import com.esdo.bepilot.Model.Response.ForgotPasswordResponse;

public interface AuthService {
    AuthResponse login(AuthRequest authenticationRequest) throws Exception;
    ForgotPasswordResponse sendOTP (ForgotPasswordRequest request);
    Boolean checkOTP (CheckOTPRequest request);
    EmployeeResponse reset(Long id, ResetPasswordRequest request);
}
