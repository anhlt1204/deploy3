package com.esdo.bepilot.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(100)")
    private String customerKey;

    @Column
    private Long idAccount ;

    @Column(columnDefinition = "VARCHAR(100)")
    private String avatar;

    @Column(columnDefinition = "VARCHAR(100)")
    private String name;

    @Column(columnDefinition = "VARCHAR(100)")
    private String email;

    @Column(columnDefinition = "VARCHAR(100)")
    private String password;

    @Column(columnDefinition = "VARCHAR(100)")
    private String phone;

    @Column
    private BigDecimal moneyRemaining;

    @Column
    private BigDecimal moneyAvailable ;

    @Column
    private String companyName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer" )
    private List<TransactionOfCustomer> transaction = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Mission> missions;


}
