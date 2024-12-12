import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import '../styles/DeliveryAddress.css';

const DeliveryAddress = () => {
  const [addresses, setAddresses] = useState([]);
  const [newAddress, setNewAddress] = useState('');
  const [selectedAddress, setSelectedAddress] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    fetchAddresses();
  }, []);

  const fetchAddresses = () => {
    axios.get('http://localhost:5000/addresses')
      .then(response => setAddresses(response.data))
      .catch(error => console.error(error));
  };

  const addAddress = () => {
    axios.post('http://localhost:5000/addresses', { address: newAddress })
      .then(response => {
        setAddresses([...addresses, response.data]);
        setNewAddress('');
      })
      .catch(error => console.error(error));
  };

  const proceedToPayment = () => {
    if (!selectedAddress) {
      alert('Please select a delivery address.');
      return;
    }
    navigate('/payment-method', { state: { selectedAddress } });
  };

  return (
    <div className="delivery-address-page">
      <h1>Select Delivery Address</h1>
      {addresses.map(address => (
        <div key={address.id}>
          <input
            type="radio"
            id={`address-${address.id}`}
            name="address"
            value={address.id}
            onChange={() => setSelectedAddress(address)}
          />
          <label htmlFor={`address-${address.id}`}>{address.address}</label>
        </div>
      ))}
      <input
        type="text"
        placeholder="Add new address"
        value={newAddress}
        onChange={(e) => setNewAddress(e.target.value)}
      />
      <button onClick={addAddress}>Add Address</button>
      <button onClick={proceedToPayment}>Proceed to Payment</button>
    </div>
  );
};

export default DeliveryAddress;
