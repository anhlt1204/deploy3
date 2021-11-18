package com.esdo.bepilot.Service.Mapper;

import com.esdo.bepilot.Model.Entity.Employee;
import com.esdo.bepilot.Model.Request.EmployeeRequest;
import com.esdo.bepilot.Model.Response.EmployeeResponse;

public class ConvertObject {
    public static Employee fromEmployeeRequestToEmployee (EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        employee.setAvatar(employeeRequest.getAvatar());
        employee.setName(employeeRequest.getName());
        employee.setPhone(employeeRequest.getPhone());

        return employee;
    }

    public static EmployeeResponse fromEmployeeToEmployeeResponse (Employee employee) {
        EmployeeResponse response = new EmployeeResponse();
        response.setId(employee.getId());
        response.setAvatar(employee.getAvatar());
        response.setName(employee.getName());
        response.setPhone(employee.getPhone());
        response.setEmail(employee.getAccount().getEmail());
        response.setPassword(employee.getAccount().getPassword());
        response.setRole(employee.getAccount().getRole());

        return response;
    }
}
