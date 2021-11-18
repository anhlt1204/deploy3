package com.esdo.bepilot.Model.Response;

import com.esdo.bepilot.Model.Entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionOfCustomerResponse {

    private BigDecimal amount ;

    private BigDecimal moneyRemaining ;

    private Timestamp createdAt  ;

    private Timestamp updatedAt ;

    private String status ;

    private String type ;

    private Long customerId ;

}
