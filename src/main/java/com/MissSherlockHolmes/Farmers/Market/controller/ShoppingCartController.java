package com.MissSherlockHolmes.Farmers.Market.controller;

import com.MissSherlockHolmes.Farmers.Market.model.ShoppingCart;
import com.MissSherlockHolmes.Farmers.Market.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCart> getCart(@PathVariable Long id) {
        return ResponseEntity.ok(shoppingCartService.getCartById(id));
    }

    @PostMapping("/")
    public ResponseEntity<ShoppingCart> createOrUpdateCart(@RequestBody ShoppingCart cart) {
        return ResponseEntity.ok(shoppingCartService.createOrUpdateCart(cart));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        shoppingCartService.deleteCart(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add")
    public ResponseEntity<ShoppingCart> addToCart(@RequestParam Long userId,
                                                  @RequestParam Long productId,
                                                  @RequestParam int quantity) {
        ShoppingCart cart = shoppingCartService.addToCart(userId, productId, quantity);
        return ResponseEntity.ok(cart);
    }
}