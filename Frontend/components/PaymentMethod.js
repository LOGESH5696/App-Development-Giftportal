import React, { useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import '../styles/PaymentMethod.css';

const PaymentMethod = () => {
  const [paymentMethod, setPaymentMethod] = useState('');
  const location = useLocation();
  const navigate = useNavigate();
  const { selectedAddress } = location.state;

  const placeOrder = () => {
    if (!paymentMethod) {
      alert('Please select a payment method.');
      return;
    }
    navigate('/order-confirmation', { state: { selectedAddress, paymentMethod } });
  };

  return (
    <div className="payment-method-page">
      <h1>Select Payment Method</h1>
      <div>
        <input
          type="radio"
          id="creditCard"
          name="paymentMethod"
          value="Credit Card"
          onChange={(e) => setPaymentMethod(e.target.value)}
        />
        <label htmlFor="creditCard">Credit Card</label>
      </div>
      <div>
        <input
          type="radio"
          id="paypal"
          name="paymentMethod"
          value="PayPal"
          onChange={(e) => setPaymentMethod(e.target.value)}
        />
        <label htmlFor="paypal">PayPal</label>
      </div>
      <div>
        <input
          type="radio"
          id="cashOnDelivery"
          name="paymentMethod"
          value="Cash on Delivery"
          onChange={(e) => setPaymentMethod(e.target.value)}
        />
        <label htmlFor="cashOnDelivery">Cash on Delivery</label>
      </div>
      <button onClick={placeOrder}>Place Order</button>
    </div>
  );
};

export default PaymentMethod;
