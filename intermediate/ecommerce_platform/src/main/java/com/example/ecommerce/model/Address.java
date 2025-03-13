package com.example.ecommerce.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String streetAddress;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private boolean isDefault;
} 