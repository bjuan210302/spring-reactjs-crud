package com.bjuan.springbackend.model;

import java.math.BigDecimal;
import java.security.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User owner;
    
    private String name;
    private String description;
    private BigDecimal price;
    private Timestamp creationDate;

    // private List<String> images; //Temporarily of type String

    @Override
    public boolean equals(Object other){
        if(other instanceof Product){
            Product p = (Product) other;
            return (this.id == p.id);
        }
        return false;
    }
}
