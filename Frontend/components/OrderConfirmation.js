import React from 'react';
import { useLocation } from 'react-router-dom';
import '../styles/OrderConfirmation.css';

const OrderConfirmation = () => {
  const location = useLocation();
  const { selectedAddress, paymentMethod } = location.state;

  return (
    <div className="order-confirmation-page">
      <h1>Order Confirmation</h1>
      <p>Thank you for your order!</p>
      <p>Delivery Address: {selectedAddress.address}</p>
      <p>Payment Method: {paymentMethod}</p>
    </div>
  );
};

export default OrderConfirmation;
