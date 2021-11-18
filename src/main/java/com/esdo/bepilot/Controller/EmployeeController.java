package com.esdo.bepilot.Controller;

import com.esdo.bepilot.Model.Request.EmployeeRequest;
import com.esdo.bepilot.Model.Response.EmployeeResponse;
import com.esdo.bepilot.Model.Response.ListEmployeeResponse;
import com.esdo.bepilot.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional(rollbackFor = Exception.class)
    EmployeeResponse createNewEmployee(@RequestBody EmployeeRequest request) {
        return employeeService.createNewEmployee(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    ListEmployeeResponse findAllEmployee(@RequestParam(defaultValue = "1", required = false) Integer page,
                                         @RequestParam(defaultValue = "10", required = false) Integer size,
                                         @RequestParam(required = false) String key) {
        return employeeService.findAllEmployee(page, size, key);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    EmployeeResponse findEmployeeById(@PathVariable(name = "id") Long id) {
        return employeeService.findEmployeeById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    EmployeeResponse editEmployeeById(@PathVariable(name = "id") Long id, @RequestBody EmployeeRequest request) {
        return employeeService.editEmployeeById(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    String deleteEmployeeById(@PathVariable(name = "id") Long id) {
        employeeService.deleteEmployeeById(id);
        return "Thành công";
    }
}
