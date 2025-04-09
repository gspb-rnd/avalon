import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import LoanApplication from '../components/LoanApplication';
import { fetchLoanApplications } from '../features/loans/loansSlice';

function Dashboard() {
  const dispatch = useDispatch();
  const { loanApplications, status, error } = useSelector(state => state.loans);

  useEffect(() => {
    dispatch(fetchLoanApplications());
  }, [dispatch]);

  return (
    <div className="dashboard">
      <h1>Avalon Loan Origination System</h1>
      <p>Welcome to the Advisor Portal</p>
      
      <div className="dashboard-content">
        <LoanApplication />
        
        {status === 'loading' && <p>Loading loan applications...</p>}
        {error && <p className="error">Error: {error}</p>}
        
        {loanApplications.length > 0 && (
          <div className="loan-applications-list">
            <h2>Your Loan Applications</h2>
            <table>
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
          </div>
        )}
      </div>
    </div>
  );
}

export default Dashboard;
