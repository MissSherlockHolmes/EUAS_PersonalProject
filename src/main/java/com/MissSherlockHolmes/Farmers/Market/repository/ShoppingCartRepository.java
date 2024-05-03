package com.MissSherlockHolmes.Farmers.Market.repository;

import com.MissSherlockHolmes.Farmers.Market.model.ShoppingCart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findByUserIdAndProductId(Long userId, Long productId);
}
