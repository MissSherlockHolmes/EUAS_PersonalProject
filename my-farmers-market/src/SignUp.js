import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './App.css';

function SignUp() {
  const [user, setUser] = useState({
    username: '',
    email: '',
    password: '',
    firstName: '',
    surname: '',
    street: '',
    city: '',
    region: '',
    country: '',
    postalCode: ''
  });
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setUser(prev => ({
      ...prev,
      [name]: value
    }));
  };

   const handleSubmit = async (e) => {
      e.preventDefault();
      const url = 'http://localhost:8080/users/register';  // Adjust if your API is hosted elsewhere
      try {
        const response = await fetch(url, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(user)
        });
        if (response.ok) {
          const newUser = await response.json();
          console.log('User registered:', newUser);
          navigate('/'); // Redirect or handle response
        } else {
          throw new Error('Failed to create user');
        }
      } catch (error) {
        console.error('Registration error:', error);
      }
    };

  return (
    <div className="form-container">
      <h1 className="form-header">Sign Up</h1>
      <div className="form-sections">
        <div className="form-section required">
          <h2>Required</h2>
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
            <button type="button" className="form-button" onClick={() => navigate('/')}>Return to Home</button>
          </form>
        </div>
        <div className="form-section optional">
          <h2>Optional</h2>
          {Object.entries(user).filter(([key]) => !['username', 'email', 'password'].includes(key)).map(([key, value]) => (
            <label key={key} className="form-label">{key.charAt(0).toUpperCase() + key.slice(1)}:
              <input type="text" name={key} className="form-input" placeholder={key.charAt(0).toUpperCase() + key.slice(1)} value={value} onChange={handleChange} />
            </label>
          ))}
        </div>
      </div>
    </div>
  );
}

export default SignUp;
