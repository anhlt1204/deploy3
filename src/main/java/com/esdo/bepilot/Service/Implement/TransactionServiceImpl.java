package com.esdo.bepilot.Service.Implement;

import com.esdo.bepilot.Model.Entity.Customer;
import com.esdo.bepilot.Model.Entity.TransactionOfCustomer;
import com.esdo.bepilot.Model.Response.CustomerResponse;
import com.esdo.bepilot.Model.Response.TransactionOfCustomerResponse;
import com.esdo.bepilot.Repository.TransactionOfCustomerRepository;
import com.esdo.bepilot.Service.Mapper.TransactionOfCustomerMapper;
import com.esdo.bepilot.Service.TransactionOfCustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionOfCustomerService {

    @Autowired
    public TransactionOfCustomerRepository transactionOfCustomerRepository ;

    @Autowired
    public TransactionOfCustomerMapper transactionOfCustomerMapper ;

    public TransactionOfCustomer create(TransactionOfCustomer transactionOfCustomer){
        log.info("Inside create of TransactionOfCustomer Service ");
//        transactionOfCustomerRepository.save(transactionOfCustomer) ;
        return null ;
    }

    public List<TransactionOfCustomer> getAllTransactionOfCustomer(){
        log.info("Inside getAllTransactionOfCustomer of TransactionOfCustomer Service ");
//        transactionOfCustomerRepository.findAll() ;
        return null ;
    }

    public List<TransactionOfCustomerResponse> getTransactionOfCustomerByCustomerId(int pageIndex, int pageSize ,Long id) {
        log.info("Inside getTransactionOfCustomerById of TransactionOfCustomer Service ");
        Pageable paging = PageRequest.of(pageIndex, pageSize);
        Page<TransactionOfCustomer> page = transactionOfCustomerRepository.getTransactionOfCustomerByCustomerId(paging, id);
        List<TransactionOfCustomer> transaction = page.getContent();
        List<TransactionOfCustomerResponse> responses = transactionOfCustomerMapper.mapToTransactionOfCustomerList(transaction) ;
//        List<TransactionOfCustomerResponse> responses = transactionOfCustomerMapper.mapToTransactionOfCustomerList(
//                transactionOfCustomerRepository.getTransactionOfCustomerByCustomerId(pageIndex, pageSize ,id)) ;
        return responses ;
    }

    public String deleteTransactionOfCustomerById(Long id) {
        log.info("Inside deleteTransactionOfCustomerById of TransactionOfCustomer Service ");
//        transactionOfCustomerRepository.deleteById(id);
        return "" ;
    }

    public TransactionOfCustomer updateTransactionOfCustomerById(TransactionOfCustomer newTransactionOfCustomer) {
        log.info("Inside updateTransactionOfCustomerById of TransactionOfCustomer Service ");
//        transactionOfCustomerRepository.save(newTransactionOfCustomer);
        return null ;
    }
}
