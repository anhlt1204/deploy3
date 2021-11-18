package com.esdo.bepilot.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "transaction_of_customer")
@NoArgsConstructor
@AllArgsConstructor
public class TransactionOfCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id ;

    private BigDecimal amount ;

    private BigDecimal MoneyRemaining ;

    private Timestamp createdAt  ;

    private Timestamp updatedAt ;

    private String status ;

    private String type ;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer ;


}
