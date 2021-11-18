package com.esdo.bepilot.Service.Implement;

import com.esdo.bepilot.Exception.InvalidException;
import com.esdo.bepilot.Model.Entity.Account;
import com.esdo.bepilot.Model.Entity.Employee;
import com.esdo.bepilot.Model.Request.AuthRequest;
import com.esdo.bepilot.Model.Request.CheckOTPRequest;
import com.esdo.bepilot.Model.Request.ForgotPasswordRequest;
import com.esdo.bepilot.Model.Request.ResetPasswordRequest;
import com.esdo.bepilot.Model.Response.AuthResponse;
import com.esdo.bepilot.Model.Response.EmployeeResponse;
import com.esdo.bepilot.Model.Response.ForgotPasswordResponse;
import com.esdo.bepilot.Repository.AccountRepository;
import com.esdo.bepilot.Repository.EmployeeRepository;
import com.esdo.bepilot.Service.AuthService;
import com.esdo.bepilot.Service.Mapper.ConvertObject;
import com.esdo.bepilot.Service.Validate.CheckValid;
import com.esdo.bepilot.Service.jwt.CustomUserDetailsService;
import com.esdo.bepilot.Util.JwtUtil;
import com.esdo.bepilot.Util.SendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private static String otp = "000000";
    private static String email = "a@gmail.com";

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public AuthResponse login(AuthRequest authenticationRequest) throws Exception {

        CheckValid.checkAuthRequest(authenticationRequest, accountRepository);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        }
        catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        UserDetails userdetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        String token = jwtUtil.generateToken(userdetails);
        Account account = accountRepository.findByEmail(authenticationRequest.getEmail());
        if (account.getEmployee() == null) {
            System.out.println("Không có thông tin nhân viên");
        }
        String name = account.getEmployee().getName();
        String avatar = account.getEmployee().getAvatar();
        String email = account.getEmail();
        String role = account.getRole();
        return new AuthResponse(name, avatar, email, role, token);
    }

    @Override
    public ForgotPasswordResponse sendOTP(ForgotPasswordRequest request) {
        CheckValid.checkForgotPasswordRequest(request, accountRepository);

        ForgotPasswordResponse response = new ForgotPasswordResponse();
        int random = 100000 + (int) (Math.random() * 99999);
        SendMail.send(request.getEmail(), "Send OTP", random, javaMailSender);

        Account account = accountRepository.findByEmail(request.getEmail());
        if (account == null) {
            throw new InvalidException("Invalid account has email = " + request.getEmail());
        }

        response.setAccountId(account.getEmployee().getId());
        otp = String.valueOf(random);
        email = account.getEmail();

        return response;
    }

    @Override
    public Boolean checkOTP(CheckOTPRequest request) {
        CheckValid.checkCheckOTPRequest(request);

        Optional<Account> accountOptional = accountRepository.findById(request.getId());

        if (accountOptional.isEmpty()) {
            throw new InvalidException("Invalid account has id = " + request.getId());
        }
        Account account = accountOptional.get();

        if (!account.getEmail().equals(email)) {
            throw new InvalidException ("Mã OTP không chính xác");
        } else if (!request.getOtp().trim().equals(otp)) {
            throw new InvalidException ("Mã OTP không chính xác");
        }

        return true;
    }

    @Override
    public EmployeeResponse reset(Long id, ResetPasswordRequest request) {
        CheckValid.checkResetPasswordRequest(request);
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isEmpty()) {
            throw new InvalidException("Invalid employee has id = " + id);
        }
        Employee employee = employeeOptional.get();

        Optional<Account> optionalAccount = accountRepository.findById(employee.getAccount().getId());
        if (optionalAccount.isEmpty()) {
            throw new InvalidException("Invalid account has id = " + id);
        }
        Account account = optionalAccount.get();
        account.setPassword(bcryptEncoder.encode(request.getNewPassword()));
        account.setEmployee(null);
        accountRepository.save(account);

        employee.setAccount(account);
        Employee employeeEdit = employeeRepository.save(employee);

        return ConvertObject.fromEmployeeToEmployeeResponse(employeeEdit);
    }
}
