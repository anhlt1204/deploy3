package com.esdo.bepilot.Service.Validate;

import com.esdo.bepilot.Exception.InvalidException;
import com.esdo.bepilot.Model.Request.*;
import com.esdo.bepilot.Repository.AccountRepository;
import com.esdo.bepilot.Repository.EmployeeRepository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckValid {

    // Kiểm tra email bắt đầu 1 hay nhiều bằng chữ cái hoặc số, có thể có '.' '_' hoặc không
    // tiếp theo là 1 hay nhiều chữ cái hoặc số
    // 1 ký tự @
    // ít nhất 2 chữ cái hoặc số
    // có thể miền cấp 1 hoặc cấp 2 (Ví dụ: .com hoặc .com.vn)
    // tên miền chỉ có từ 2 đến 4 ký tự
    private static final String regexEmail = "^([\\w]+[\\.\\_]?[\\w]+)@[\\w]{2,}(\\.[\\w]{2,4}){1,2}$";

    // Kiểm tra số bắt đầu bằng 0 hoặc +84 và đằng sau là 9 chữ số
    private static final String regexPhone = "^(0|\\+84)[0-9]{9}$";

    private static final String regexPassword = "^(?=.*\\d)(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$";

    static Pattern pattern;
    static Matcher matcher;

    public static Boolean isEmail(String email) {
        pattern = Pattern.compile(regexEmail);
        matcher = pattern.matcher(email);
        return matcher.find();
    }

    public static Boolean isPhone(String phone) {
        pattern = Pattern.compile(regexPhone);
        matcher = pattern.matcher(phone);
        return matcher.find();
    }

    public static Boolean isPassword(String password) {
        pattern = Pattern.compile(regexPassword);
        matcher = pattern.matcher(password);
        return matcher.find();
    }

    public static void checkAuthRequest(AuthRequest request, AccountRepository accountRepository) {
        if (request.getEmail().trim().length() == 0) {
            throw new InvalidException("Email is required");
        }

        if (request.getPassword().trim().length() == 0) {
            throw new InvalidException("Password is required");
        }

        if (!isEmail(request.getEmail())) {
            throw new InvalidException("Invalid Email");
        }

        if (!isPassword(request.getPassword())) {
            throw new InvalidException("Password must be eight characters including one letter, one number character");
        }

        List<String> emails = new ArrayList<>();
        accountRepository.findAll().forEach(account -> emails.add(account.getEmail()));

        int flag = 0;
        for (String e : emails) {
            if (e.equals(request.getEmail().trim())) {
                flag = 1;
                break;
            }
        }

        if (flag == 0) {
            throw new InvalidException("Tài khoản " + request.getEmail().trim() +" chưa tồn tại. Vui lòng kiểm tra lại.");
        }
    }

    public static void checkEmployeeRequest(EmployeeRequest request, AccountRepository accountRepository,
                                            EmployeeRepository employeeRepository, boolean isEdit) {
        if (request.getAvatar().trim().length() == 0) {
            throw new InvalidException("Avatar is required");
        }

//        File avatar = new File(request.getAvatar().trim());
//
//        if (!avatar.exists()) {
//            throw new InvalidException("Invalid avatar");
//        }

        if (request.getName().trim().length() == 0) {
            throw new InvalidException("Name is required");
        }

        if (request.getEmail().trim().length() == 0) {
            throw new InvalidException("Email is required");
        }

        if (request.getPassword().trim().length() == 0) {
            throw new InvalidException("Password is required");
        }

        if (request.getPhone().trim().length() == 0) {
            throw new InvalidException("Phone is required");
        }

        if (request.getRole().trim().length() == 0) {
            throw new InvalidException("Role is required");
        }

        if (!isPhone(request.getPhone().trim())) {
            throw new InvalidException("Invalid Phone");
        }

        if (!isEmail(request.getEmail().trim())) {
            throw new InvalidException("Invalid Email");
        }

        if (!isPassword(request.getPassword())) {
            throw new InvalidException("Password must be eight characters including one letter, one number character");
        }

        List<String> phones = new ArrayList<>();
        employeeRepository.findAll().forEach(employee -> phones.add(employee.getPhone()));

        List<String> emails = new ArrayList<>();
        accountRepository.findAll().forEach(account -> emails.add(account.getEmail()));

        if (!isEdit) {
            for (String p : phones) {
                if (p.equals(request.getPhone().trim())) {
                    throw new InvalidException("Số điện thoại " + p + " đã tồn tại, vui lòng sử dụng số điện thoại khác");
                }
            }

            for (String e : emails) {
                if (e.equals(request.getEmail().trim())) {
                    throw new InvalidException("Email " + e + " đã tồn tại, vui lòng sử dụng email khác");
                }
            }
        }

        if (!request.getRole().trim().equals("ADMIN")) {
            if (!request.getRole().trim().equals("EMPLOYEE")) {
                throw new InvalidException("Role must be is ADMIN or EMPLOYEE");
            }
        }
    }

    public static void checkAccountInputDTO(AccountInputDTO request, AccountRepository accountRepository) {
        if (request.getEmail().trim().length() == 0) {
            throw new InvalidException("Email is required");
        }

        if (request.getPassword().trim().length() == 0) {
            throw new InvalidException("Password is required");
        }

        if (request.getRole().trim().length() == 0) {
            throw new InvalidException("Role is required");
        }

        if (!isEmail(request.getEmail())) {
            throw new InvalidException("Invalid Email");
        }

        if (!isPassword(request.getPassword())) {
            throw new InvalidException("Password must be eight characters including one letter, one number character");
        }

        List<String> emails = new ArrayList<>();
        accountRepository.findAll().forEach(account -> emails.add(account.getEmail()));

        for (String e : emails) {
            if (e.equals(request.getEmail().trim())) {
                throw new InvalidException("Email " + e + " đã tồn tại, vui lòng sử dụng email khác");
            }
        }

        if (!request.getRole().trim().equals("ADMIN")) {
            if (!request.getRole().trim().equals("EMPLOYEE")) {
                throw new InvalidException("Role must be is ADMIN or EMPLOYEE");
            }
        }
    }

    public static void checkForgotPasswordRequest(ForgotPasswordRequest request, AccountRepository accountRepository) {
        if (request.getEmail().trim().length() == 0) {
            throw new InvalidException("Email is required");
        }

        if (!isEmail(request.getEmail())) {
            throw new InvalidException("Invalid Email");
        }

        List<String> emails = new ArrayList<>();
        accountRepository.findAll().forEach(account -> emails.add(account.getEmail()));

        int flag = 0;

        for (String e : emails) {
            if (e.equals(request.getEmail().trim())) {
                flag = 1;
                break;
            }
        }

        if (flag == 0) {
            throw new InvalidException("Email " + request.getEmail() + " chưa có tài khoản.");
        }
    }

    public static void checkCheckOTPRequest(CheckOTPRequest request) {
        if (request.getId() <= 0) {
            throw new InvalidException("Invalid account id");
        }

        if (request.getOtp().trim().length() == 0) {
            throw new InvalidException("OTP is required");
        }
    }

    public static void checkResetPasswordRequest(ResetPasswordRequest request) {
        if (request.getNewPassword().trim().length() == 0) {
            throw new InvalidException("New Password is required");
        }

        if (request.getReNewPassword().trim().length() == 0) {
            throw new InvalidException("Renew Password is required");
        }

        if (request.getNewPassword().trim().length() == 0) {
            throw new InvalidException("New Password is required");
        }

        if (request.getReNewPassword().trim().length() == 0) {
            throw new InvalidException("Renew Password is required");
        }

        if (!request.getNewPassword().trim().equals(request.getReNewPassword().trim())) {
            throw new InvalidException("Renew Password is diffirent to New Password");
        }
    }
}
