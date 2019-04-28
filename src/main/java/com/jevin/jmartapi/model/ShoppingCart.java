package com.jevin.jmartapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private String status;

    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", timezone = "Asia/Colombo")
    private Date createdDate;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("shoppingCart")
    private Set<ShoppingCartProduct> shoppingCartProducts;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Set<ShoppingCartProduct> getShoppingCartProducts() {
        return shoppingCartProducts;
    }

    public void setShoppingCartProducts(Set<ShoppingCartProduct> shoppingCartProducts) {
        this.shoppingCartProducts = shoppingCartProducts;
    }
}
