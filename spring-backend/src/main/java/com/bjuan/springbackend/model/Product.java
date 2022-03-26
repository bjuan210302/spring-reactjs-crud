package com.bjuan.springbackend.model;

import java.math.BigDecimal;
import java.security.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Product {

    @Id @GeneratedValue
    private long id;
    
    private String name;
    private String description;
    private BigDecimal price;
    private Timestamp creationDate;

    // private List<String> images; //Temporarily of type String

}
