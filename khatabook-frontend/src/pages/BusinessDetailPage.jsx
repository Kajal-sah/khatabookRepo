import React from 'react';
import { useParams } from 'react-router-dom';

function BusinessDetailPage() {
  const { businessId } = useParams();

  return (
    <div>
      <h1>Business Detail Page</h1>
      <p>Business ID: {businessId}</p>
      {/* Show list of customers for this business */}
    </div>
  );
}

export default BusinessDetailPage;
