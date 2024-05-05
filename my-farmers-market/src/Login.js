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

    const handleSubmit = (e) => {
        e.preventDefault();
        // Here you would typically handle authentication
        console.log('Logging in with:', credentials);
        // Navigate to the dashboard or home page upon successful login
        navigate('/'); // Adjust as necessary for your application routing
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
            </form>
        </div>
    );
}

export default Login;
