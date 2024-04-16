package com.MissSherlockHolmes.Farmers.Market.repository;

import com.MissSherlockHolmes.Farmers.Market.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    // Finds all products by category, returns them as a List
    List<Product> findByCategory(String category);

    // Override to specify return type as List instead of Iterable
    @Override
    List<Product> findAll();
}
