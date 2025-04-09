import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import LoanApplication from '../components/LoanApplication';
import { fetchLoanApplications, resetStatus } from '../features/loans/loansSlice';

function Dashboard() {
  const dispatch = useDispatch();
  const { loanApplications, status, error } = useSelector(state => state.loans);
  const [refreshKey, setRefreshKey] = useState(0);

  useEffect(() => {
    dispatch(resetStatus());
    dispatch(fetchLoanApplications());
  }, [dispatch, refreshKey]);

  const refreshApplications = () => {
    setRefreshKey(oldKey => oldKey + 1);
  };

  return (
    <div className="dashboard">
      <h1>Avalon Loan Origination System</h1>
      <p>Welcome to the Advisor Portal</p>
      
      <div className="dashboard-content">
        <LoanApplication onSubmitSuccess={refreshApplications} />
        
        {status === 'loading' && <p>Loading loan applications...</p>}
        {error && <p className="error">Error: {error}</p>}
        
        <div className="loan-applications-section">
          <div className="section-header">
            <h2>Your Loan Applications</h2>
            <button onClick={refreshApplications} className="refresh-button">Refresh</button>
          </div>
          
          {Array.isArray(loanApplications) && loanApplications.length > 0 ? (
            <table className="loan-applications-table">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Client</th>
                  <th>Amount</th>
                  <th>Status</th>
                  <th>Created Date</th>
                </tr>
              </thead>
              <tbody>
                {loanApplications.map(loan => (
                  <tr key={loan.id}>
                    <td>{loan.id}</td>
                    <td>{loan.clientId}</td>
                    <td>${loan.loanTerms.amount.toLocaleString()}</td>
                    <td>{loan.status}</td>
                    <td>{new Date(loan.createdDate).toLocaleDateString()}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          ) : (
            <p>No loan applications found. Create your first application above.</p>
          )}
        </div>
      </div>
    </div>
  );
}

export default Dashboard;
