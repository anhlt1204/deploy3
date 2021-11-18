package com.esdo.bepilot.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "sub_config")
@NoArgsConstructor
@AllArgsConstructor
public class SubConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(100)")
    private String communication;

    @Column(columnDefinition = "VARCHAR(100)")
    private String type;

    @Column(columnDefinition = "int default 0")
    private Integer minTime;

    @Column(columnDefinition = "int default 0")
    private Integer maxTime;

    @Column
    private BigDecimal customerPay;

    @Column
    private BigDecimal userReceived;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JsonIgnore
    private Config config;
}
