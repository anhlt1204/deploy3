package com.esdo.bepilot.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "account")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(100)",unique = true)
    private String email;

    @Column(columnDefinition = "VARCHAR(100)")
    private String password;

    @Column(columnDefinition = "VARCHAR(20)")
    private String role;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    private Integer Status;

    @OneToOne(mappedBy = "account", cascade = CascadeType.MERGE)
    private Employee employee;
}