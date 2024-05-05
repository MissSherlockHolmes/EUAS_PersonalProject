// Home.js
import React from 'react';
import { Link } from 'react-router-dom';

function Home() {
  return (
    <>
      <h1 className="market-title">Farmers Market</h1>
      <img src="/vegetables.jpg" className="vegetables-img" alt="Vegetables" />
      <div className="sections-container">
        <div className="section">
          <h2>For Farmers</h2>
          <Link to="/add-product"><button>Add Product</button></Link>
        </div>
        <div className="section">
          <h2>For Shoppers</h2>
          <Link to="/sign-up"><button>Sign Up</button></Link>
          <Link to="/login"><button>Login</button></Link>
        </div>
      </div>
    </>
  );
}

export default Home;
