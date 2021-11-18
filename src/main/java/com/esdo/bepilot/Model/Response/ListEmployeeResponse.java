package com.esdo.bepilot.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListEmployeeResponse {
    List<EmployeeResponse> employeeList = new ArrayList<>();
    int page;
    int size;
    int totalPages;
    int totalItems;
}
