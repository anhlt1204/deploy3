package com.esdo.bepilot.Service;

import com.esdo.bepilot.Model.Request.EmployeeRequest;
import com.esdo.bepilot.Model.Response.EmployeeResponse;
import com.esdo.bepilot.Model.Response.ListEmployeeResponse;

public interface EmployeeService {
    EmployeeResponse createNewEmployee(EmployeeRequest employeeRequest);
    ListEmployeeResponse findAllEmployee(Integer page, Integer size, String key);
    EmployeeResponse findEmployeeById(Long id);
    EmployeeResponse editEmployeeById(Long id, EmployeeRequest request);
    void deleteEmployeeById(Long id);
}
