package com.esdo.bepilot.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "mission")
@AllArgsConstructor
@NoArgsConstructor
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(100)")
    private String missionKey;

    @Column(columnDefinition = "VARCHAR(100)")
    private String name;

    @Column(columnDefinition = "VARCHAR(100)")
    private String communication;

    @Column(columnDefinition = "VARCHAR(100)")
    private String missionType;

    @Column(columnDefinition = "int default 0")
    private Integer quantity;

    @Column(columnDefinition = "int default 0")
    private Integer quantityMade;

    @Column(columnDefinition = "VARCHAR(100)")
    private String keyWord;

    @Column(columnDefinition = "VARCHAR(100)")
    private String link;

    @Column
    private BigDecimal priceUnit;

    @Column
    private BigDecimal moneyReceived;

    @CreatedDate
    @Column(columnDefinition = "timestamp default now()")
    private OffsetDateTime createAt;

    @LastModifiedDate
    @UpdateTimestamp
    @Column(columnDefinition = "timestamp default now()")
    private OffsetDateTime updateAt;

    @Column(columnDefinition = "VARCHAR(100)")
    private Integer deadTime;

    @Column(columnDefinition = "VARCHAR(20)")
    private String status;

    @ManyToOne
    @JoinColumn(name = "customer_id",referencedColumnName = "id")
    @JsonIgnore
    private Customer customer;

    @OneToMany(mappedBy = "mission",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<MissionDetail> missionDetails;

    @PrePersist
    void onCreate(){
        this.setCreateAt(OffsetDateTime.now());
    }


}
