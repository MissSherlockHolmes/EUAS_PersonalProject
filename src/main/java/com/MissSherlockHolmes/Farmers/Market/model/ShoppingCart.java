package com.MissSherlockHolmes.Farmers.Market.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User user; // Link to the User entity

    @ManyToMany
    @JoinTable(
            name = "shopping_cart_products",
            joinColumns = @JoinColumn(name = "shopping_cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products = new ArrayList<>();

    public ShoppingCart() {
        // JPA only
    }

    public ShoppingCart(User user) {
        this.user = user;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    // Utility method to add a product to the cart
    public void addProduct(Product product) {
        this.products.add(product);
    }

    // Utility method to remove a product from the cart
    public void removeProduct(Product product) {
        this.products.remove(product);
    }
}
