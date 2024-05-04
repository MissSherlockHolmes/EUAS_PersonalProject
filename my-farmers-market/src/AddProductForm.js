import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './AddProductForm.css';

function AddProductForm() {
    const navigate = useNavigate();
    const [product, setProduct] = useState({
        name: '',
        price: '',
        category: '',
        description: '',
        quantity: ''
    });
    const [searchTerm, setSearchTerm] = useState('');
    const [searchResult, setSearchResult] = useState(null);
    const [showDeleteConfirmation, setShowDeleteConfirmation] = useState(false);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setProduct(prev => ({
            ...prev,
            [name]: value
        }));
    };

    const handleSearchChange = (e) => {
        setSearchTerm(e.target.value);
    };

     const handleReturnHome = () => {
            navigate('/'); // Use navigate to redirect to the homepage
        };

    const handleKeyPress = (e) => {
        if (e.key === 'Enter') {
            handleSearch(e);
        }
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const productToSubmit = {
            name: product.name,
            price: parseFloat(product.price),
            category: product.category,
            description: product.description,
            maxQuantity: parseInt(product.quantity, 10),
            remainingQuantity: parseInt(product.quantity, 10)
        };

        fetch('http://localhost:8080/products', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: JSON.stringify(productToSubmit)
        })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Something went wrong: ' + response.status);
            }
        })
        .then(data => {
            alert('Product submitted successfully!');
            console.log('Success:', data);
            setProduct({
                name: '',
                price: '',
                category: '',
                description: '',
                quantity: ''
            });
        })
        .catch((error) => {
            console.error('Error:', error);
            alert('Failed to add product: ' + error.message);
        });
    };

    const handleSearch = (e) => {
        e.preventDefault();
        fetch(`http://localhost:8080/products/search?name=${encodeURIComponent(searchTerm)}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Product not found');
            }
        })
        .then(data => {
            setSearchResult(data); // Update searchResult with the data received
            console.log('Received data:', data);
            console.log('Search result:', data);
        })
        .catch(error => {
            console.error('Search failed:', error);
            alert('Search failed: ' + error.message);
            setSearchResult(null);
        });
    };

    const handleDeleteConfirmation = () => {
        setShowDeleteConfirmation(true);
    };

    const handleDelete = (id) => {
        if (!searchResult || !Array.isArray(searchResult) || searchResult.length === 0) {
            alert('No product selected for deletion');
            return;
        }

        const productToDelete = searchResult.find(result => result.id === id);
        if (!productToDelete) {
            alert('No product selected for deletion');
            return;
        }

        if (!window.confirm(`Delete "${productToDelete.name}"? This action is permanent and cannot be undone.`)) {
            return;
        }

        fetch(`http://localhost:8080/products/${id}`, {
            method: 'DELETE'
        })
        .then(response => {
            if (response.ok) {
                alert('Product successfully deleted');
                const updatedSearchResult = searchResult.filter(result => result.id !== id);
                setSearchResult(updatedSearchResult);
                setShowDeleteConfirmation(false);
            } else {
                throw new Error('Failed to delete product');
            }
        })
        .catch(error => {
            console.error('Deletion failed:', error);
            alert('Deletion failed: ' + error.message);
        });
    };

    return (
        <div className="form-container">
            <div className="form-section">
                <h1 className="form-header">Add New Product</h1>
                <form onSubmit={handleSubmit}>
                    {/* Product Input Fields */}
                    <label className="form-label">Name: <input type="text" name="name" value={product.name} onChange={handleChange} className="form-input" required /></label>
                    <label className="form-label">Price: <input type="text" name="price" value={product.price} onChange={handleChange} className="form-input" required placeholder="$0.00" /></label>
                    <label className="form-label">Category: <input type="text" name="category" value={product.category} onChange={handleChange} className="form-input" required /></label>
                    <label className="form-label">Description: <textarea name="description" value={product.description} onChange={handleChange} className="form-textarea" required /></label>
                    <label className="form-label">Quantity: <input type="number" name="quantity" value={product.quantity} onChange={handleChange} className="form-input" required /></label>
                    <button type="submit" className="form-button">Add Product</button>
                    <button onClick={handleReturnHome} className="form-button">Return to Home</button>
                </form>
            </div>
           <div className="form-section">
               <h2 className="form-header">Search and Delete Product</h2>
               <input
                   type="text"
                   value={searchTerm}
                   onChange={handleSearchChange}
                   onKeyPress={handleKeyPress}
                   placeholder="Search by name"
                   className="form-input"
               />
               <button onClick={handleSearch} className="form-button">Search</button>
               {searchResult && searchResult.length > 0 ? (
                   searchResult.map((result, index) => (
                       <div key={index}>
                           <p>Name: {result.name}</p>
                           <p>Price: {result.price}</p>
                           <p>Category: {result.category}</p>
                           <p>Description: {result.description}</p>
                           <p>Quantity: {result.remainingQuantity}</p>
                           {!showDeleteConfirmation ? (
                               <button onClick={() => handleDeleteConfirmation(result.id)} className="form-button">Delete</button>
                           ) : (
                               <>
                                   <p>Are you sure you want to delete this product? This action cannot be undone.</p>
                                   <button onClick={() => handleDelete(result.id)} className="form-button">Confirm Delete</button>
                                   <button onClick={() => setShowDeleteConfirmation(false)} className="form-button">Cancel</button>
                               </>
                           )}
                       </div>
                   ))
               ) : null}
           </div>
        </div>
    );
}

export default AddProductForm;
