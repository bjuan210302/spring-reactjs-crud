package com.bjuan.springbackend.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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

    @JsonManagedReference
    @OneToMany(
        targetEntity=Product.class, // Owned type
        mappedBy = "owner", // Owner variable in owned class
        fetch = FetchType.EAGER, // So i can call the whole collection
        orphanRemoval = true) // Deletes all owned when owner is deleted 
    private List<Product> products;

    public void addProduct(Product product){
        product.setOwner(this);
        products.add(product);
    }

    // THIS THROWS NULL AND I DONT KNOW WHY
    // OTHER METHODS USING THIS COLLECTION WORK OK ???????
    public int countProducts(){
        System.out.println(products);
        return products.size();
    }

    // THIS THROWS NULL AND I DONT KNOW WHY
    // OTHER METHODS USING THIS COLLECTION WORK OK ???????
    public Product getProduct(Long id){
        for(Product p: products){
            if(p.getId() == id){
                return p;
            }
        }
        return null;
    }

    public void deleteProduct(Product product){
        products.remove(product);
        product.setOwner(null);
    }

}
