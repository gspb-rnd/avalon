import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { createLoanApplication } from '../features/loans/loansSlice';
import './LoanApplication.css';

const LoanApplication = () => {
  const dispatch = useDispatch();
  const [formData, setFormData] = useState({
    clientId: '',
    loanAmount: '',
    loanTermMonths: '',
    interestRate: '',
    purpose: '',
    collateralType: '',
    collateralValue: ''
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    dispatch(createLoanApplication({
      clientId: formData.clientId,
      loanTerms: {
        amount: parseFloat(formData.loanAmount),
        termMonths: parseInt(formData.loanTermMonths),
        interestRate: parseFloat(formData.interestRate),
        purpose: formData.purpose
      },
      collateral: {
        type: formData.collateralType,
        estimatedValue: parseFloat(formData.collateralValue)
      }
    }));
    setFormData({
      clientId: '',
      loanAmount: '',
      loanTermMonths: '',
      interestRate: '',
      purpose: '',
      collateralType: '',
      collateralValue: ''
    });
  };

  return (
    <div className="loan-application">
      <h2>New Loan Application</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="clientId">Client ID</label>
          <input
            type="text"
            id="clientId"
            name="clientId"
            value={formData.clientId}
            onChange={handleChange}
            required
          />
        </div>
        
        <div className="form-group">
          <label htmlFor="loanAmount">Loan Amount ($)</label>
          <input
            type="number"
            id="loanAmount"
            name="loanAmount"
            value={formData.loanAmount}
            onChange={handleChange}
            required
            min="1000"
          />
        </div>
        
        <div className="form-group">
          <label htmlFor="loanTermMonths">Term (Months)</label>
          <input
            type="number"
            id="loanTermMonths"
            name="loanTermMonths"
            value={formData.loanTermMonths}
            onChange={handleChange}
            required
            min="1"
          />
        </div>
        
        <div className="form-group">
          <label htmlFor="interestRate">Interest Rate (%)</label>
          <input
            type="number"
            id="interestRate"
            name="interestRate"
            value={formData.interestRate}
            onChange={handleChange}
            required
            step="0.01"
            min="0"
          />
        </div>
        
        <div className="form-group">
          <label htmlFor="purpose">Loan Purpose</label>
          <select
            id="purpose"
            name="purpose"
            value={formData.purpose}
            onChange={handleChange}
            required
          >
            <option value="">Select Purpose</option>
            <option value="REAL_ESTATE">Real Estate</option>
            <option value="BUSINESS_EXPANSION">Business Expansion</option>
            <option value="INVESTMENT">Investment</option>
            <option value="DEBT_CONSOLIDATION">Debt Consolidation</option>
            <option value="OTHER">Other</option>
          </select>
        </div>
        
        <div className="form-group">
          <label htmlFor="collateralType">Collateral Type</label>
          <select
            id="collateralType"
            name="collateralType"
            value={formData.collateralType}
            onChange={handleChange}
            required
          >
            <option value="">Select Collateral Type</option>
            <option value="REAL_ESTATE">Real Estate</option>
            <option value="SECURITIES">Securities</option>
            <option value="VEHICLE">Vehicle</option>
            <option value="EQUIPMENT">Equipment</option>
            <option value="OTHER">Other</option>
          </select>
        </div>
        
        <div className="form-group">
          <label htmlFor="collateralValue">Estimated Collateral Value ($)</label>
          <input
            type="number"
            id="collateralValue"
            name="collateralValue"
            value={formData.collateralValue}
            onChange={handleChange}
            required
            min="0"
          />
        </div>
        
        <button type="submit" className="submit-button">Submit Application</button>
      </form>
    </div>
  );
};

export default LoanApplication;
