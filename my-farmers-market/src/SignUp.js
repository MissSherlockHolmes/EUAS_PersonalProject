import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './App.css';  // Make sure this path is correct and points to your CSS file with the styles

function SignUp() {
  const [user, setUser] = useState({
    username: '',
    email: '',
    password: ''
  });
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setUser(prev => ({
      ...prev,
      [name]: value
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log('Registering:', user);
    navigate('/');  // Redirect to homepage or dashboard upon successful registration
  };

  return (
      <div className="form-container">
          <h1 className="form-header">Sign Up</h1>
          <form onSubmit={handleSubmit}>
              <label className="form-label">Username:
                  <input type="text" name="username" className="form-input" placeholder="Username" value={user.username} onChange={handleChange} required />
              </label>
              <label className="form-label">Email:
                  <input type="email" name="email" className="form-input" placeholder="Email" value={user.email} onChange={handleChange} required />
              </label>
              <label className="form-label">Password:
                  <input type="password" name="password" className="form-input" placeholder="Password" value={user.password} onChange={handleChange} required />
              </label>
              <button type="submit" className="form-button">Register</button>
          </form>
      </div>
  );
}

export default SignUp;
