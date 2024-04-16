package com.MissSherlockHolmes.Farmers.Market.model;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Table("product")  // Specifies the database table
public class Product {
    // Getters and setters
    @Id  // Marks 'id' as the primary key
    private Long id;

    @Column("name")  // Specifies the column name if different from field name
    private String name;

    @Column("price")
    private Double price;

    private String category;  // Fruits, Vegetables, etc., column name defaults to field name

    @Column("description")
    private String description;

    // Default constructor
    public Product() {
    }

    // Constructor with all fields
    public Product(String name, Double price, String category, String description) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
