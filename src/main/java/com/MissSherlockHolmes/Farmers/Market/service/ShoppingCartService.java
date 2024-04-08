package com.MissSherlockHolmes.Farmers.Market.service;

import com.MissSherlockHolmes.Farmers.Market.model.Product;
import com.MissSherlockHolmes.Farmers.Market.model.ShoppingCart;
import com.MissSherlockHolmes.Farmers.Market.model.User;
import com.MissSherlockHolmes.Farmers.Market.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public ShoppingCart createShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        return shoppingCartRepository.save(shoppingCart);
    }

    public Optional<ShoppingCart> getShoppingCartById(Long id) {
        return shoppingCartRepository.findById(id);
    }

    public ShoppingCart addProductToCart(Long cartId, Product product) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("ShoppingCart not found for id " + cartId));
        shoppingCart.getProducts().add(product);
        return shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCart removeProductFromCart(Long cartId, Product product) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("ShoppingCart not found for id " + cartId));
        shoppingCart.getProducts().remove(product);
        return shoppingCartRepository.save(shoppingCart);
    }

}
