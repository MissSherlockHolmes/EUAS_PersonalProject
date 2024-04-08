package com.MissSherlockHolmes.Farmers.Market.repository;

import com.MissSherlockHolmes.Farmers.Market.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(String category);
}

