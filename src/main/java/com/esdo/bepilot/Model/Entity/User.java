package com.esdo.bepilot.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(100)")
    private String userKey;

    private String idAccount ;

    @Column(columnDefinition = "VARCHAR(100)")
    private String avatar;

    @Column(columnDefinition = "VARCHAR(100)")
    private String name;

    @Column(columnDefinition = "VARCHAR(100)")
    private String email;

    @Column(columnDefinition = "VARCHAR(100)")
    private String phone;

    private BigDecimal amountMoneyReceive;

    @Column
    private BigDecimal moneyWithdrawn;

    @Column
    private BigDecimal moneyRemaining;

    private int numberOfMissionDone;

    @OneToMany(mappedBy = "user")
    private List<Withdrawn> withdrawnList = new ArrayList<>()  ;

    @OneToMany(mappedBy = "users",cascade = CascadeType.ALL)
    private List<MissionDetail> missionDetailList;
}
