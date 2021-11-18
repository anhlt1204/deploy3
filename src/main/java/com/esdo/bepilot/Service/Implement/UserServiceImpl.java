package com.esdo.bepilot.Service.Implement;

import com.esdo.bepilot.Model.Entity.User;
import com.esdo.bepilot.Model.Entity.Withdrawn;
import com.esdo.bepilot.Model.Request.UserRequest;
import com.esdo.bepilot.Model.Response.UserResponse;
import com.esdo.bepilot.Repository.UserRepository;
import com.esdo.bepilot.Service.Mapper.UserMapper;
import com.esdo.bepilot.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    public UserRepository userRepository ;

    @Autowired
    public UserMapper userMapper ;

    public UserResponse create(UserRequest request){
        log.info("Inside create of User Service ");

        User user = userMapper.mapToUserRequest(request) ;
        user.setWithdrawnList(new ArrayList<>()) ;
        user.setMissionDetailList(new ArrayList<>());
        user.setMoneyRemaining(BigDecimal.ZERO);
        user.setMoneyWithdrawn(BigDecimal.ZERO);
        user.setNumberOfMissionDone(0);
        userRepository.save(user) ;
        List<User> userClones = userRepository.getUserClone() ;
        userClones.get(0).setUserKey("ND" + userClones.get(0).getId());
        userRepository.save(userClones.get(0)) ;

        return userMapper.mapToUserEntity(user) ;
    }

    public List<UserResponse> getAllUser(int pageIndex , int size){
        log.info("Inside getAllUser of User Service ");
        Pageable paging = PageRequest.of(pageIndex, size);
        Page<User> page = userRepository.findAll(paging);
        List<User> users = page.getContent();
        List<UserResponse> responses = userMapper.mapToListUserEntity(users) ;
        return responses ;
    }

    public UserResponse getUserById(Long id) {
        log.info("Inside getUserById of User Service ");
        Optional<User> user = userRepository.findById(id) ;
        UserResponse response =  userMapper.mapToUserEntity(user.get()) ;
        return response ;
    }

    public String deleteUserById(Long id) {
        log.info("Inside deleteUserById of User Service ");
        userRepository.deleteById(id);
        return "Delete Complete" ;
    }

    public UserResponse updateUserById(Long id, String name , String phone) {
        log.info("Inside updateUserById of User Service ");
        userRepository.saveUser(id, name , phone) ;
        return userMapper.mapToUserEntity(userRepository.findById(id).get()) ;
    }
}
