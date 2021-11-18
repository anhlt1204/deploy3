package com.esdo.bepilot.Service;


import com.esdo.bepilot.Model.Entity.TransactionOfCustomer;
import com.esdo.bepilot.Model.Response.TransactionOfCustomerResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionOfCustomerService {

    TransactionOfCustomer create(TransactionOfCustomer transactionOfCustomer);

    List<TransactionOfCustomer> getAllTransactionOfCustomer();

    List<TransactionOfCustomerResponse> getTransactionOfCustomerByCustomerId(int pageIndex , int pageSize , Long id);

    String deleteTransactionOfCustomerById(Long id) ;

    TransactionOfCustomer updateTransactionOfCustomerById(TransactionOfCustomer newTransactionOfCustomer) ;
}