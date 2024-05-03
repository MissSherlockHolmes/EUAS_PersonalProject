package com.MissSherlockHolmes.Farmers.Market.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data  // Generates getters, setters, equals, hashCode, and toString methods.
public class ShoppingCart {
    private Long id;
    private Long userId; // Matches 'user_id' with a foreign key to 'user(id)'
    private LocalDateTime createdAt; // Maps to 'created_at'
    private LocalDateTime updatedAt; // Maps to 'updated_at'
    private Long productId; // Matches 'product_id' with a foreign key to 'product(id)'
    private Integer quantity;
    private Long orderId; // Assuming this also references an Order table
    private Long bookingId; // Optional reference, possibly to a Booking table
    private String status;
    private LocalDateTime orderedAt; // Maps to 'ordered_at'
    private LocalDateTime bookedAt; // Maps to 'booked_at'
    private Integer maxQuantity; // Maps to 'max_quantity'
    private Integer remainingQuantity; // Maps to 'remaining_quantity'

    // Constructor without args for creating instances without initial data.
    public ShoppingCart() {
    }

    // Additional constructors and methods as necessary.
}
