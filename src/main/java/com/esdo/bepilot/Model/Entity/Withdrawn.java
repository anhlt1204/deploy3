package com.esdo.bepilot.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "withdrawn")
@NoArgsConstructor
@AllArgsConstructor
public class Withdrawn {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @ManyToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
    private User user ;

    private BigDecimal amount ;

    private BigDecimal moneyRemaining ;

    private Timestamp createdAt ;

    private Timestamp updatedAt ;

    private String status ;

}
