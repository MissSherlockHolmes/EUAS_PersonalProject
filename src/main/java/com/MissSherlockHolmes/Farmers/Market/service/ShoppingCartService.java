package com.MissSherlockHolmes.Farmers.Market.service;

import com.MissSherlockHolmes.Farmers.Market.model.Product;
import com.MissSherlockHolmes.Farmers.Market.model.ShoppingCart;
import com.MissSherlockHolmes.Farmers.Market.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ShoppingCart getCartById(Long cartId) {
        String sql = "SELECT * FROM shopping_cart WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{cartId}, (rs, rowNum) -> {
            ShoppingCart cart = new ShoppingCart();
            cart.setId(rs.getLong("id"));
            cart.setUserId(rs.getLong("user_id"));
            // Consider fetching and setting additional details if necessary
            return cart;
        });
    }

    public ShoppingCart createOrUpdateCart(ShoppingCart cart) {
        if (cart.getId() == null) {
            String insertSql = "INSERT INTO shopping_cart (user_id, created_at) VALUES (?, ?)";
            jdbcTemplate.update(insertSql, new Object[]{cart.getUserId(), LocalDateTime.now()}, new int[]{Types.BIGINT, Types.TIMESTAMP});
            return cart; // Assuming cart's id is auto-generated. You might need to fetch the last insert ID if required.
        } else {
            String updateSql = "UPDATE shopping_cart SET user_id = ?, updated_at = ? WHERE id = ?";
            jdbcTemplate.update(updateSql, new Object[]{cart.getUserId(), LocalDateTime.now(), cart.getId()}, new int[]{Types.BIGINT, Types.TIMESTAMP, Types.BIGINT});
            return cart;
        }
    }

    public void deleteCart(Long id) {
        String sql = "DELETE FROM shopping_cart WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public ShoppingCart addToCart(Long userId, Long productId, int quantity) {
        Optional<ShoppingCart> cartOptional = findCartByUserId(userId);
        ShoppingCart cart = cartOptional.orElse(new ShoppingCart());

        cart.setUserId(userId);
        updateCartDetails(cart, productId, quantity);

        return createOrUpdateCart(cart);
    }

    private Optional<ShoppingCart> findCartByUserId(Long userId) {
        String sql = "SELECT * FROM shopping_cart WHERE user_id = ?";
        List<ShoppingCart> carts = jdbcTemplate.query(sql, new Object[]{userId}, (rs, rowNum) -> {
            ShoppingCart cart = new ShoppingCart();
            cart.setId(rs.getLong("id"));
            cart.setUserId(rs.getLong("user_id"));
            return cart;
        });
        return carts.stream().findFirst();
    }

    private void updateCartDetails(ShoppingCart cart, Long productId, int quantity) {
        // You should include the logic to manage products within the cart.
        // This might involve inserting a new row in a cart_products link table, or updating an existing row.
        String sql = "INSERT INTO shopping_cart_products (cart_id, product_id, quantity) VALUES (?, ?, ?) ON CONFLICT (cart_id, product_id) DO UPDATE SET quantity = excluded.quantity";
        jdbcTemplate.update(sql, new Object[]{cart.getId(), productId, quantity}, new int[]{Types.BIGINT, Types.BIGINT, Types.INTEGER});
    }
}
