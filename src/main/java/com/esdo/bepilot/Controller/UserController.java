package com.esdo.bepilot.Controller;

import com.esdo.bepilot.Model.Entity.MissionDetail;
import com.esdo.bepilot.Model.Entity.User;
import com.esdo.bepilot.Model.Entity.Withdrawn;
import com.esdo.bepilot.Model.Request.UserRequest;
import com.esdo.bepilot.Model.Response.MissionDetailResponse;
import com.esdo.bepilot.Model.Response.ResponseEntity;
import com.esdo.bepilot.Model.Response.UserResponse;
import com.esdo.bepilot.Model.Response.WithdrawnResponse;
import com.esdo.bepilot.Service.Implement.MissionDetailServiceImpl;
import com.esdo.bepilot.Service.Implement.UserServiceImpl;
import com.esdo.bepilot.Service.Implement.WithdrawnServiceImpl;
import com.esdo.bepilot.Service.MissionDetailService;
import com.esdo.bepilot.Service.Validate.UserValidate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1")
@CrossOrigin
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @Autowired
    WithdrawnServiceImpl withdrawnService;

    @Autowired
    MissionDetailServiceImpl missionDetailService;

    @Autowired
    UserValidate userValidate;

    @GetMapping(value = "/users")
    public ResponseEntity<List<UserResponse>> getAllUser(@RequestParam(value = "pageIndex", defaultValue = "0",
            required = false) int pageIndex,
                                                         @RequestParam(value = "pageSize", defaultValue = "10",
                                                                 required = false) int pageSize) {
        ResponseEntity response = new ResponseEntity();
        log.info("Inside getAllUser of customerAPI ");
        List<UserResponse> responses = userService.getAllUser(pageIndex, pageSize);
        response.setData(responses);
        response.setPage(pageIndex);
        response.setSize(pageSize);
        response.setTotalPage(responses.size() / pageSize);
        response.setTotalObject(responses.size());
        return response;
    }

    @PostMapping(value = "/users/add")
    public User createUser(@RequestBody UserRequest request) {
        log.info("Inside createUser of userAPI ");
        userValidate.validate(request);
        userService.create(request);
        return null;
    }

    @GetMapping(value = "users/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        log.info("Inside getUserById of userAPI ");
        return userService.getUserById(id);
    }

    @GetMapping(value = "users/{id}/history-withdrawn")
    public ResponseEntity<List<WithdrawnResponse>> getListHistoryWithdrawn(@RequestParam(value = "pageIndex", defaultValue = "0",
            required = false) int pageIndex,
                                                                           @RequestParam(value = "pageSize", defaultValue = "10",
                                                                                   required = false) int pageSize,
                                                                           @PathVariable Long id) {
        log.info("Inside getListHistoryWithdrawn of userAPI ");
        ResponseEntity<List<WithdrawnResponse>> response = new ResponseEntity<>();
        //TODO lấy danh sách withdrawn theo id người dùng
        List<WithdrawnResponse> responses = withdrawnService.getWithdrawnByUserId(pageIndex, pageSize, id);
        response.setData(responses);
        response.setPage(pageIndex);
        response.setSize(pageSize);
        response.setTotalPage(responses.size() / pageSize);
        response.setTotalObject(responses.size());

        return response;
    }

    @PutMapping(value = "/users/{id}")
    public UserResponse updateUser(@RequestParam(name = "name") String name,
                                   @RequestParam(name = "phone") String phone,
                                   @PathVariable long id) {
        log.info("Inside updateUser of userAPI ");
        return userService.updateUserById(id, name, phone);
    }

    @DeleteMapping(value = "/users/delete")
    public User deleteUser(@RequestParam Long id) {
        log.info("Inside updateUser of userAPI ");
        userService.deleteUserById(id);
        return null;
    }

    @GetMapping(value = "users/{id}/missiondetail")
    public ResponseEntity<List<MissionDetailResponse>> getListMissionDetail(@PathVariable Long id,
                                                                            @RequestParam(name = "pageIndex") int pageIndex,
                                                                            @RequestParam(name = "pageSize") int pageSize) {
        ResponseEntity response = new ResponseEntity();
        log.info("Inside getListMissionDetail of customerAPI ");
        List<MissionDetailResponse> responses = missionDetailService.getMissionDetailByUserId(pageIndex, pageSize, id);
        response.setData(responses);
        response.setPage(pageIndex);
        response.setSize(pageSize);
        response.setTotalPage(responses.size() / pageSize);
        response.setTotalObject(responses.size());

        return response;
    }


}
