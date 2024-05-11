package com.MissSherlockHolmes.Farmers.Market.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

    @Getter
    @Setter
    @Table("product")  // Specifies the database table
    public class Product {
        // Getters and setters
        @Id  // Marks 'id' as the primary key
        private Long id;

        @Column("name")  // Specifies the column name if different from field name
        private String name;

        @Column("price")
        private Double price;

        @Column("category")
        private String category;  // Fruits, Vegetables, etc., column name defaults to field name

        @Column("description")
        private String description;

        @Getter
        @Column("max_quantity")
        private Integer maxQuantity;

        @Getter
        @Column("remaining_quantity")
        private Integer remainingQuantity;

        // Default constructor
        public Product() {
        }

        // Constructor with all fields
        public Product(String name, Double price, String category, String description, Integer maxQuantity, Integer remainingQuantity) {
            this.name = name;
            this.price = price;
            this.category = category;
            this.description = description;
            this.maxQuantity = maxQuantity;
            this.remainingQuantity = remainingQuantity;
        }

    }

