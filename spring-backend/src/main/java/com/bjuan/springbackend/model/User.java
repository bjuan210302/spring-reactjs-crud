package com.bjuan.springbackend.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private String name;
    private String surname;
    private String email;
    private String password;

    @OneToMany(targetEntity=Product.class)
    private List<Product> products;

    public void addProduct(Product product){
        this.products.add(product);
    }

    public Product deleteProduct(Product product){
        for(Product p: products){
            if(p.getId() == product.getId()){
                products.remove(p);
                return p;
            }
        }
        return null;
    }

}
