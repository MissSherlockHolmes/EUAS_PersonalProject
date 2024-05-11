import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './App.css';

function Login() {
    const [credentials, setCredentials] = useState({
        username: '',
        password: ''
    });
    const navigate = useNavigate();

    const handleChange = (e) => {
        const { name, value } = e.target;
        setCredentials(prev => ({
            ...prev,
            [name]: value
        }));
    };

const handleSubmit = async (e) => {
    e.preventDefault();
    const url = 'http://localhost:8080/users/login';  // Adjust URL based on your actual API
    try {
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(credentials)
        });
        if (response.ok) {
            const user = await response.json();
            console.log('Login successful:', user);
            navigate('/products'); // Navigate to products page upon successful login
        } else {
            throw new Error('Failed to log in');
        }
    } catch (error) {
        console.error('Login error:', error);
    }
};

    return (
        <div className="form-container">
            <h1 className="form-header">Login</h1>
            <form onSubmit={handleSubmit}>
                <label className="form-label">Username:
                    <input type="text" name="username" placeholder="Username" value={credentials.username} onChange={handleChange} className="form-input" required />
                </label>
                <label className="form-label">Password:
                    <input type="password" name="password" placeholder="Password" value={credentials.password} onChange={handleChange} className="form-input" required />
                </label>
                <button type="submit" className="form-button">Login</button>
                <button type="button" className="form-button" onClick={() => navigate('/')}>Return to Home</button>
            </form>
        </div>
    );
}

export default Login;
