package com.MissSherlockHolmes.Farmers.Market.repository;

import com.MissSherlockHolmes.Farmers.Market.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    // Finds all products by category, returns them as a List
    List<Product> findByCategory(String category);

    // Find product by name
    Product findByName(String name);

    // Custom query to search products by name containing the provided search term
    List<Product> findByNameContainingIgnoreCase(String name);
}