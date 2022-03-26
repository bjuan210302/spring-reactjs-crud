package com.bjuan.springbackend.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class User {

    @Id
    private long id;
    
    private String name;
    private String surname;
    private String email;
    private String password;

    @OneToMany(targetEntity=Product.class)
    private List<Product> products;

}
