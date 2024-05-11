import React from 'react';
import { useNavigate } from 'react-router-dom';

function WishlistPage({ selectedProducts, removeFromCart }) {
    const navigate = useNavigate();

    return (
        <div className="wishlist-container">
            <h1>Your Cart</h1>
            <button onClick={() => navigate('/products')}>Back to Products</button>
            <div className="wishlist-grid">
                {selectedProducts.map(item => (
                    <div key={item.id} className="wishlist-item">
                        <p>{item.name} - ${item.price}</p>
                        <button onClick={() => removeFromCart(item.id)}>Remove</button>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default WishlistPage;
