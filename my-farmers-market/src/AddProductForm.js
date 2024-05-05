import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './AddProductForm.css';

function AddProductForm() {
    const navigate = useNavigate();
    const [product, setProduct] = useState({
        id: null,
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
        navigate('/'); // Navigate back to the homepage
    };

    const handleKeyPress = (e) => {
        if (e.key === 'Enter') {
            handleSearch(e);
        }
    };

    const handleSubmit = (e) => {
        if (e) e.preventDefault(); // Only call preventDefault if event exists

        const method = product.id ? 'PUT' : 'POST';
        const url = `http://localhost:8080/products${product.id ? '/' + product.id : ''}`;

        fetch(url, {
            method: method,
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: JSON.stringify({
                name: product.name,
                price: parseFloat(product.price),
                category: product.category,
                description: product.description,
                maxQuantity: parseInt(product.quantity, 10),
                remainingQuantity: parseInt(product.quantity, 10)
            })
        })
        .then(response => {
            if (!response.ok) throw new Error('Failed to update');
            return response.json();
        })
        .then(data => {
            alert('Product updated successfully!');
            setProduct({
                id: null,
                name: '',
                price: '',
                category: '',
                description: '',
                quantity: ''
            });
        })
        .catch((error) => {
            console.error('Failed to update product:', error);
            alert(`Failed to update product: ${error.message}`);
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
        .then(response => response.json())
        .then(data => {
            setSearchResult(data);
        })
        .catch(error => {
            alert('Search failed: ' + error.message);
            setSearchResult(null);
        });
    };

       const handleEdit = (product) => {
           setProduct({
               id: product.id,
               name: product.name,
               price: product.price.toString(),
               category: product.category,
               description: product.description,
               quantity: product.remainingQuantity.toString()
           });
       };

    const handleDeleteConfirmation = (id) => {
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
                setSearchResult(prev => prev.filter(item => item.id !== id));
                setShowDeleteConfirmation(false);
            } else {
                throw new Error('Failed to delete product');
            }
        })
        .catch(error => {
            alert('Deletion failed: ' + error.message);
        });
    };

 const clearForm = () => {
     setSearchTerm(''); // Reset search term
     setSearchResult(null); // Reset search results
 };

    return (
        <div className="form-container">
            <div className="form-section">
                <h1 className="form-header">{product.id ? 'Edit' : 'Add New'} Product</h1>
                <form onSubmit={handleSubmit}>
                    {/* Product Input Fields */}
                    <label className="form-label">Name: <input type="text" name="name" value={product.name} onChange={handleChange} className="form-input" required /></label>
                    <label className="form-label">Price: <input type="text" name="price" value={product.price} onChange={handleChange} className="form-input" required placeholder="$0.00" /></label>
                    <label className="form-label">Category: <input type="text" name="category" value={product.category} onChange={handleChange} className="form-input" required /></label>
                    <label className="form-label">Description: <textarea name="description" value={product.description} onChange={handleChange} className="form-textarea" required /></label>
                    <label className="form-label">Quantity: <input type="number" name="quantity" value={product.quantity} onChange={handleChange} className="form-input" required /></label>
                    <button type="submit" className="form-button">{product.id ? 'Update' : 'Add'} Product</button>
                    <button type="button" onClick={handleReturnHome} className="form-button">Return to Home</button>
                </form>
            </div>
            <div className="form-section">
                <h2 className="form-header">Search and Delete Product</h2>
                <input type="text" value={searchTerm} onChange={handleSearchChange} onKeyPress={handleKeyPress} placeholder="Search by name" className="form-input" />
                <button onClick={handleSearch} className="form-button">Search</button>
                <button type="button" onClick={clearForm} className="form-button">Clear Form</button>
                {searchResult && searchResult.map((result, index) => (
                    <div key={index}>
                        <p>Name: {result.name}</p>
                        <p>Price: {result.price}</p>
                        <p>Category: {result.category}</p>
                        <p>Description: {result.description}</p>
                        <p>Quantity: {result.remainingQuantity}</p>
                        <button onClick={() => handleEdit(result)} className="form-button">Edit</button>
                        <button onClick={() => handleDeleteConfirmation(result.id)} className="form-button">Delete</button>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default AddProductForm;
