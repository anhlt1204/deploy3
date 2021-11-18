package com.esdo.bepilot.Controller;

import com.esdo.bepilot.Model.Entity.TransactionOfCustomer;
import com.esdo.bepilot.Model.Request.CustomerRequest;
import com.esdo.bepilot.Model.Response.CustomerResponse;
import com.esdo.bepilot.Model.Response.ResponseEntity;
import com.esdo.bepilot.Model.Response.TransactionOfCustomerResponse;
import com.esdo.bepilot.Service.Implement.CustomerServiceImpl;
import com.esdo.bepilot.Service.Implement.TransactionServiceImpl;
import com.esdo.bepilot.Service.Validate.CustomerValidate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1")
@CrossOrigin
public class CustomerController {
    @Autowired
    CustomerServiceImpl customerService;

    @Autowired
    TransactionServiceImpl transactionOfCustomerService;

    @Autowired
    CustomerValidate customerValidate;


    @GetMapping(value = "/customers")
    public ResponseEntity<List<CustomerResponse>> getAllCustomer(@RequestParam(value = "pageIndex", defaultValue = "0",
            required = false) int pageIndex,
                                                                 @RequestParam(value = "pageSize", defaultValue = "10",
                                                                         required = false) int pageSize) {
        ResponseEntity response = new ResponseEntity();
        log.info("Inside getAllCustomer of customerAPI ");
        List<CustomerResponse> responses = customerService.getAllCustomer(pageIndex, pageSize);
        response.setData(responses);
        response.setPage(pageIndex);
        response.setSize(pageSize);
        response.setTotalPage(responses.size() / pageSize);
        response.setTotalObject(responses.size());

        return response;
    }

    @PostMapping(value = "/customers/add")
    public CustomerResponse createCustomer(@RequestBody @Valid CustomerRequest request) {
        log.info("Inside createCustomer of customerAPI ");
        customerValidate.validate(request);
        customerService.create(request);
        return null;
    }

    @GetMapping(value = "customers/{id}")
    public CustomerResponse getCustomerById(@PathVariable Long id) {
        log.info("Inside getCustomerById of customerAPI ");
        CustomerResponse customerResponse = customerService.getCustomerById(id);
        return customerResponse;
    }

    @GetMapping(value = "customers/{id}/deposited")
    public ResponseEntity<List<TransactionOfCustomerResponse>> getListHistoryDeposited(@RequestParam(value = "pageIndex", defaultValue = "0",
            required = false) int pageIndex,
                                                                                       @RequestParam(value = "pageSize", defaultValue = "10",
                                                                                               required = false) int pageSize,
                                                                                       @PathVariable Long id) {
        log.info("Inside getListHistoryDeposited of customerAPI ");
        ResponseEntity response = new ResponseEntity();
        log.info("Inside getAllCustomer of customerAPI ");
        List<TransactionOfCustomerResponse> responses = transactionOfCustomerService.getTransactionOfCustomerByCustomerId(pageIndex, pageSize, id);
        response.setData(responses);
        response.setPage(pageIndex);
        response.setSize(pageSize);
        response.setTotalPage(responses.size() / pageSize);
        response.setTotalObject(responses.size());
        return response;

    }

    @PutMapping(value = "/customers/{id}")
    public CustomerResponse updateCustomer(@RequestParam(name = "phone") String phone,
                                           @RequestParam(name = "name") String name,
                                           @PathVariable(name = "id") long id) {
        log.info("Inside updateCustomer of customerAPI ");
        return customerService.updateCustomerById(id, name, phone);
    }

    @DeleteMapping(value = "/customers/delete")
    public void deleteCustomer(@RequestParam Long id) {
        log.info("Inside updateCustomer of customerAPI ");
        customerService.deleteCustomerById(id);
    }

    @GetMapping(value = "/customers/search")
    public ResponseEntity<List<CustomerResponse>> searchCustomer(@RequestParam(value = "pageIndex", defaultValue = "0",
            required = false) int pageIndex,
                                                                 @RequestParam(value = "pageSize", defaultValue = "10",
                                                                         required = false) int pageSize,
                                                                 @RequestParam(name = "keyWord") String keyWord) {

        if (keyWord == null) {
            keyWord = "";
        }
        ResponseEntity response = new ResponseEntity();
        log.info("Inside getAllCustomer of customerAPI ");
        List<CustomerResponse> responses = customerService.searchCustomer(pageIndex, pageSize, keyWord);
        response.setData(responses);
        response.setPage(pageIndex);
        response.setSize(pageSize);
        response.setTotalPage(responses.size() / pageSize);
        response.setTotalObject(responses.size());

        return response;
    }
}
