package com.esdo.bepilot.Service;

import com.esdo.bepilot.Model.Entity.Customer;
import com.esdo.bepilot.Model.Request.CustomerRequest;
import com.esdo.bepilot.Model.Response.CustomerResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    CustomerResponse create(CustomerRequest customer);

    List<CustomerResponse> getAllCustomer(int pageIndex , int pageSize);

    CustomerResponse getCustomerById(Long id);

    String deleteCustomerById(Long id) ;

    CustomerResponse updateCustomerById(Long id , String name , String phone) ;
}

