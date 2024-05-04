// App.js
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import './App.css';
import Home from './Home';
import AddProductForm from './AddProductForm';
import SignUp from './SignUp';  // Import the SignUp component
import Login from './Login';  // Import the Login component

function App() {
  return (
    <Router>
      <div className="app-container">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/add-product" element={<AddProductForm />} />
          <Route path="/sign-up" element={<SignUp />} />  // Route for the SignUp component
          <Route path="/login" element={<Login />} />  // Route for the Login component
        </Routes>
      </div>
    </Router>
  );
}

export default App;
