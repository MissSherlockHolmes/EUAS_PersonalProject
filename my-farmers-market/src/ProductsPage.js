import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './ProductsPage.css'; // Import CSS file for styling
import { CartProvider, useCart } from "react-use-cart";

function ProductsPage() {
    const [products, setProducts] = useState([]);
    const [cart, setCart] = useState({});
    const [maxQuantityReached, setMaxQuantityReached] = useState(false);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchProducts = async () => {
            try {
                const response = await fetch('http://localhost:8080/products'); // Adjust URL
                if (response.ok) {
                    const products = await response.json();
                    setProducts(products);
                } else {
                    console.error('Failed to fetch products');
                    navigate('/login'); // Redirect to login if fetch fails
                }
            } catch (error) {
                console.error('Error fetching products:', error);
            }
        };

        fetchProducts();
    }, [navigate]);

    function addToCart(product, quantityInput) {
        const quantity = parseInt(quantityInput);
        if (quantity > 0 && quantity <= product.max_quantity) {
            setCart(currentCart => {
                const newCart = {...currentCart};
                const existingProduct = newCart[product.id];

                const updatedQuantity = existingProduct ? existingProduct.quantity + quantity : quantity;
                if (updatedQuantity <= product.max_quantity) {
                    newCart[product.id] = { ...product, quantity: updatedQuantity };
                    return newCart;
                } else {
                    alert('Max quantity reached');
                    return currentCart;
                }
            });
        }
    }

    return (
        <div className="products-container">
            <h1 className="products-heading">Products</h1>
            <button type="button" className="form-button" onClick={() => navigate('/')}>Return to Home</button>
            <div className="products-grid">
                {products.map(product => (
                    <div key={product.id} className="product-item">
                        <p className="product-name">{product.name}</p>
                        <p className="product-price">${product.price}</p>
                        <div className="quantity-container">
                            <input
                                id={`quantity-${product.id}`}
                                type="number"
                                min="1"
                                max={product.max_quantity}
                                defaultValue="1"
                                className="quantity-input"
                            />
                           <button
                               className="add-to-cart-button"
                               onClick={() => addToCart(product, parseInt(document.getElementById(`quantity-${product.id}`).value))}
                           >
                               Add to Cart
                           </button>
                            {maxQuantityReached && cart[product.id] && cart[product.id].quantity >= product.max_quantity &&
                                <p className="max-quantity-notice">Max quantity added</p>}
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default ProductsPage;
