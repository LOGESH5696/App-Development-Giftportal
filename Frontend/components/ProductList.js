import React, { useEffect, useState } from 'react';
import axios from 'axios';
import '../styles/ProductList.css';
import { useNavigate } from 'react-router-dom';

const ProductList = () => {
  const [products, setProducts] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    axios.get('http://localhost:5000/products')
      .then(response => setProducts(response.data))
      .catch(error => console.error(error));
  }, []);

  const addToCart = (product, quantity = 1) => {
    axios.get(`http://localhost:5000/cart?productId=${product.id}`)
      .then(response => {
        if (response.data.length > 0) {
          // If product already in cart, update the quantity
          const existingItem = response.data[0];
          updateCartItem(existingItem.id, existingItem.quantity + quantity);
        } else {
          // If product not in cart, add new item
          const cartItem = {
            productId: product.id,
            name: product.name,
            price: product.price,
            imageUrl: product.imageUrl,
            quantity
          };
          axios.post('http://localhost:5000/cart', cartItem)
            .then(() => {
              console.log("Product added to cart");
            })
            .catch(error => console.error("Error adding to cart", error));
        }
      })
      .catch(error => {
        console.error("Error fetching cart items", error);
        navigate('/login'); // Redirect to login if not logged in
      });
  };

  const updateCartItem = (id, quantity) => {
    axios.patch(`http://localhost:5000/cart/${id}`, { quantity })
      .then(() => {
        console.log("Cart item updated");
      })
      .catch(error => console.error("Error updating cart item", error));
  };

  const handleShare = (product) => {
    const shareData = {
      title: product.name,
      text: `Check out this product: ${product.name}`,
      url: window.location.href
    };

    if (navigator.share) {
      navigator.share(shareData)
        .then(() => console.log('Share successful'))
        .catch(error => console.error('Error sharing', error));
    } else {
      navigator.clipboard.writeText(window.location.href)
        .then(() => alert('Product link copied to clipboard'))
        .catch(error => console.error('Error copying to clipboard', error));
    }
  };

  return (
    <div className="product-list">
      {products.map(product => (
        <div key={product.id} className="product-card">
          <img src={product.imageUrl} alt={product.name} />
          <h3>{product.name}</h3>
          <p>{product.description}</p>
          <div className="product-rating-container">
            <span className="product-rating">{product.rating} ★</span>
            <span className="product-rating-count">({product.count})</span>
          </div>
          <p>${product.price.toFixed(2)}</p>
          <label>
            Quantity:
            <select onChange={(e) => addToCart(product, parseInt(e.target.value))}>
              {[...Array(10).keys()].map(n => (
                <option key={n + 1} value={n + 1}>{n + 1}</option>
              ))}
            </select>
          </label>
          <button onClick={() => addToCart(product)}>Add to Cart</button>
          <button onClick={() => handleShare(product)}>Share</button>
        </div>
      ))}
    </div>
  );
};

export default ProductList;
