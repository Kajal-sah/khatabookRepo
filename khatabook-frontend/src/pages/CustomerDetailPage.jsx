import React from 'react';
import { useParams } from 'react-router-dom';

function CustomerDetailPage() {
  const { customerId } = useParams();

  return (
    <div>
      <h1>Customer Detail Page</h1>
      <p>Customer ID: {customerId}</p>
      {/* Show transactions for this customer */}
    </div>
  );
}

export default CustomerDetailPage;
