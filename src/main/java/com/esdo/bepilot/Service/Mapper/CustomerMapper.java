package com.esdo.bepilot.Service.Mapper;

import com.esdo.bepilot.Model.Entity.Customer;
import com.esdo.bepilot.Model.Request.CustomerRequest;
import com.esdo.bepilot.Model.Response.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerMapper {

    @Autowired
    MissionMapper missionMapper ;

    @Autowired
    TransactionOfCustomerMapper transactionOfCustomerMapper ;

    /**
     * convert dữ liệu từ entity sang Response
     *
     * @param customer
     * @return
     */
    public CustomerResponse mapToCustomerEntity(Customer customer) {
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setId(customer.getId());
        customerResponse.setCustomerKey(customer.getCustomerKey());
        customerResponse.setAvatar(customer.getAvatar());
        customerResponse.setName(customer.getName());
        customerResponse.setPhone(customer.getPhone());
        customerResponse.setEmail(customer.getEmail());
        customerResponse.setMoneyRemaining(customer.getMoneyRemaining());
        customerResponse.setMoneyAvailable(customer.getMoneyAvailable());
        customerResponse.setCompanyName(customer.getCompanyName());
        //TODO : chỉnh sửa list mission và transaction
        customerResponse.setMissionList(missionMapper.mapToListResponse(customer.getMissions()));
        customerResponse.setTransaction(transactionOfCustomerMapper.mapToTransactionOfCustomerList(customer.getTransaction()));
        return customerResponse;
    }

    /**
     * convert dữ liệu từ request sang entity
     *
     * @param request
     * @return
     */
    public Customer mapToCustomerRequest(CustomerRequest request) {
        Customer customer = new Customer();
        // TODO : converter từ customer request sang customer entity
        customer.setName(request.getName());
        customer.setAvatar(request.getAvatar());
        customer.setEmail(request.getEmail());
        customer.setPassword(request.getPassword()) ;
        customer.setPhone(request.getPhone()) ;
        customer.setCompanyName(request.getCompanyName());
        return customer;
    }

    /**
     * convert dữ liệu từ list entity sang list Response
     *
     * @param customers
     * @return
     */
    public List<CustomerResponse> mapToListCustomerEntity(List<Customer> customers) {
        List<CustomerResponse> customerResponses = new ArrayList<>() ;
        customers.forEach(customer -> {
            customerResponses.add(this.mapToCustomerEntity(customer)) ;
        });
        return customerResponses;
    }



}
