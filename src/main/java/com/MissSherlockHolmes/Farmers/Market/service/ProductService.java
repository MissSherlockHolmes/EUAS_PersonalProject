package com.MissSherlockHolmes.Farmers.Market.service;

import com.MissSherlockHolmes.Farmers.Market.model.Product;
import com.MissSherlockHolmes.Farmers.Market.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }
}
