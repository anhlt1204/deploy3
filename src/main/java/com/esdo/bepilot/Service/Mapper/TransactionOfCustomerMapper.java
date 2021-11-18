package com.esdo.bepilot.Service.Mapper;

import com.esdo.bepilot.Model.Entity.TransactionOfCustomer;
import com.esdo.bepilot.Model.Response.TransactionOfCustomerResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionOfCustomerMapper {


    public TransactionOfCustomerResponse mapToTransactionOfCustomerEntity(TransactionOfCustomer entity) {
        TransactionOfCustomerResponse response = new TransactionOfCustomerResponse();
        response.setAmount(entity.getAmount());
        response.setMoneyRemaining(entity.getMoneyRemaining());
        response.setType(entity.getType());
        response.setStatus(entity.getStatus());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        response.setCustomerId(entity.getCustomer().getId());

        return response;
    }

    public List<TransactionOfCustomerResponse> mapToTransactionOfCustomerList (List<TransactionOfCustomer> list) {
        List<TransactionOfCustomerResponse> responseList = new ArrayList<>() ;
        list.forEach(transactionOfCustomer -> {
            responseList.add(this.mapToTransactionOfCustomerEntity(transactionOfCustomer)) ;
        });

        return  responseList ;
    }

}

