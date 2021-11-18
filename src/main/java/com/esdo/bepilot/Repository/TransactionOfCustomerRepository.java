package com.esdo.bepilot.Repository;

import com.esdo.bepilot.Model.Entity.TransactionOfCustomer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface TransactionOfCustomerRepository extends JpaRepository<TransactionOfCustomer, Long> {

    @Query(value = "SELECT t FROM TransactionOfCustomer t where t.customer.id = ?1 and t.type = 'deposited'  ")
    Page<TransactionOfCustomer> getTransactionOfCustomerByCustomerId(Pageable paging, Long id) ;
}
