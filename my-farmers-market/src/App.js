import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import './App.css';
import Home from './Home';
import AddProductForm from './AddProductForm';
import SignUp from './SignUp';
import Login from './Login';
import ProductsPage from './ProductsPage';
import WishlistPage from './WishlistPage';

function App() {
    // Initialize the cart and selectedProducts state
    const [cart, setCart] = useState({});
    const [selectedProducts, setSelectedProducts] = useState([]);

    // Function to add selected products to the cart
    const addToCart = (products) => {
        setSelectedProducts(products);
    };

    // Function to remove an item from the cart
    const removeFromCart = (productId) => {
        setSelectedProducts(selectedProducts.filter(item => item.id !== productId));
    };

    return (
        <Router>
            <div className="app-container">
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/add-product" element={<AddProductForm />} />
                    <Route path="/sign-up" element={<SignUp />} />
                    <Route path="/login" element={<Login />} />
                    <Route path="/products" element={<ProductsPage addToCart={addToCart} />} />
                    <Route path="/wishlist" element={<WishlistPage selectedProducts={selectedProducts} removeFromCart={removeFromCart} />} />
                </Routes>
            </div>
        </Router>
    );
}

export default App;
