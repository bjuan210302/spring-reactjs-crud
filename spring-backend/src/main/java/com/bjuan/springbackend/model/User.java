package com.bjuan.springbackend.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Entity
@Data
public class User {

    @Id @GeneratedValue
    private long id;
    
    private String name;
    private String surname;
    private String email;
    private String password;

    private List<Product> products;

}
