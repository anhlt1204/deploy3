package com.esdo.bepilot.Service.Mapper;

import com.esdo.bepilot.Model.Entity.User;
import com.esdo.bepilot.Model.Entity.Withdrawn;
import com.esdo.bepilot.Model.Request.UserRequest;
import com.esdo.bepilot.Model.Response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    @Autowired
    MissionDetailMapper missionDetailMapper ;
    @Autowired
    WithdrawnMapper withdrawnMapper ;

    /**
     * convert dữ liệu từ entity sang Response
     *
     * @param user
     * @return
     */
    public UserResponse mapToUserEntity(User user) {
        UserResponse userResponse = new UserResponse();
        // TODO : converter từ user entity sang user response
        userResponse.setId(user.getId());
        userResponse.setUserKey(user.getUserKey());
        userResponse.setAvatar(user.getAvatar());
        userResponse.setName(user.getName());
        userResponse.setPhone(user.getPhone());
        userResponse.setEmail(user.getEmail());
        userResponse.setAmountMoneyReceive(user.getAmountMoneyReceive());
        userResponse.setMoneyWithdrawn(user.getMoneyWithdrawn());
        userResponse.setMoneyRemaining(user.getMoneyRemaining());
        userResponse.setNumberOfMissionDone(user.getNumberOfMissionDone());
        //TODO converter list mission và withdrawn
        userResponse.setMissionDetailList(missionDetailMapper.mapToListMissionDetail(user.getMissionDetailList()));
        user.getWithdrawnList().forEach(withdrawn -> {
            userResponse.getWithdrawnList().add(withdrawnMapper.mapToWithdrawn(withdrawn)) ;
        });
        return userResponse ;
    }

    /**
     * convert dữ liệu từ request sang entity
     *
     * @param request
     * @return
     */
    public User mapToUserRequest(UserRequest request) {
        User user = new User();
        user.setName(request.getName()) ;
        user.setPhone(request.getPhone());
        user.setUserKey(request.getUserKey());
        user.setIdAccount(request.getIdAccount());
        user.setAvatar(request.getAvatar());
        user.setEmail(request.getEmail());
        user.setAmountMoneyReceive(request.getAmountMoneyReceive());
        user.setMoneyWithdrawn(request.getMoneyWithdrawn());
        user.setMoneyRemaining(request.getMoneyRemaining());
        return user;
    }

    /**
     * convert dữ liệu từ list entity sang list Response
     *
     * @param users
     * @return
     */
    public List<UserResponse> mapToListUserEntity(List<User> users) {
        List<UserResponse> userResponses = new ArrayList<>() ;
        // TODO : converter từ list user entity sang list user response
        users.forEach(user -> {
            userResponses.add(this.mapToUserEntity(user)) ;
        });
        return userResponses;
    }

    /**
     * convert dữ liệu từ list request sang list entity
     *
     * @param requests
     * @return
     */
    public List<User> mapToListUserRequest(List<UserRequest> requests) {
        List<User> users = new ArrayList<>() ;
        // TODO : converter từ list user request sang list user entity
        return users;
    }
}
