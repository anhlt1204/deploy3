package com.esdo.bepilot.Service;

import com.esdo.bepilot.Model.Entity.User;
import com.esdo.bepilot.Model.Request.UserRequest;
import com.esdo.bepilot.Model.Response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserResponse create(UserRequest user);

    List<UserResponse> getAllUser(int pageIndex , int size);

    UserResponse getUserById(Long id);

    String deleteUserById(Long id) ;

    UserResponse updateUserById(Long id , String name , String phone) ;
}