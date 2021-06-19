package com.ken.backend.entity;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="customer")
public class Customer {

    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC") Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "create_time")
    private LocalDateTime createTime;
}
