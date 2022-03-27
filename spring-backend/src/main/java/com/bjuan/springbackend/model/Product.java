package com.bjuan.springbackend.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

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

    @JsonBackReference
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User owner;
    
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDateTime creationDate;

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
