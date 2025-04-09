import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

const API_URL = 'https://d2kbjdljdoxcy0.cloudfront.net/api';

const mockLoanApplications = [
  {
    id: '550e8400-e29b-41d4-a716-446655440000',
    clientId: 'C12345',
    status: 'PENDING_REVIEW',
    createdDate: '2025-04-09T04:00:00Z',
    loanTerms: {
      amount: 250000,
      termMonths: 60,
      interestRate: 4.5,
      purpose: 'BUSINESS_EXPANSION'
    },
    collateral: {
      type: 'REAL_ESTATE',
      estimatedValue: 350000
    }
  }
];

export const createLoanApplication = createAsyncThunk(
  'loans/createLoanApplication',
  async (loanData, { rejectWithValue }) => {
    try {
      const newApplication = {
        id: `loan-${Date.now()}`,
        clientId: loanData.clientId,
        status: 'PENDING_REVIEW',
        createdDate: new Date().toISOString(),
        loanTerms: loanData.loanTerms,
        collateral: loanData.collateral
      };
      mockLoanApplications.push(newApplication);
      return newApplication;
    } catch (error) {
      return rejectWithValue(error.message || 'An error occurred while processing your request');
    }
  }
);

export const fetchLoanApplications = createAsyncThunk(
  'loans/fetchLoanApplications',
  async (_, { rejectWithValue }) => {
    try {
      return mockLoanApplications;
    } catch (error) {
      return rejectWithValue(error.message || 'Failed to fetch loan applications');
    }
  }
);

export const fetchLoanApplicationById = createAsyncThunk(
  'loans/fetchLoanApplicationById',
  async (id, { rejectWithValue }) => {
    try {
      const loan = mockLoanApplications.find(loan => loan.id === id);
      if (!loan) {
        throw new Error('Loan application not found');
      }
      return loan;
    } catch (error) {
      return rejectWithValue(error.message || 'An error occurred while processing your request');
    }
  }
);

const initialState = {
  loanApplications: [],
  currentLoanApplication: null,
  status: 'idle', // 'idle' | 'loading' | 'succeeded' | 'failed'
  error: null
};

const loansSlice = createSlice({
  name: 'loans',
  initialState,
  reducers: {
    resetStatus: (state) => {
      state.status = 'idle';
      state.error = null;
    }
  },
  extraReducers: (builder) => {
    builder
      .addCase(createLoanApplication.pending, (state) => {
        state.status = 'loading';
      })
      .addCase(createLoanApplication.fulfilled, (state, action) => {
        state.status = 'succeeded';
        state.loanApplications.push(action.payload);
        state.currentLoanApplication = action.payload;
      })
      .addCase(createLoanApplication.rejected, (state, action) => {
        state.status = 'failed';
        state.error = action.payload || 'Failed to create loan application';
      })
      
      .addCase(fetchLoanApplications.pending, (state) => {
        state.status = 'loading';
      })
      .addCase(fetchLoanApplications.fulfilled, (state, action) => {
        state.status = 'succeeded';
        state.loanApplications = action.payload;
      })
      .addCase(fetchLoanApplications.rejected, (state, action) => {
        state.status = 'failed';
        state.error = action.payload || 'Failed to fetch loan applications';
      })
      
      .addCase(fetchLoanApplicationById.pending, (state) => {
        state.status = 'loading';
      })
      .addCase(fetchLoanApplicationById.fulfilled, (state, action) => {
        state.status = 'succeeded';
        state.currentLoanApplication = action.payload;
      })
      .addCase(fetchLoanApplicationById.rejected, (state, action) => {
        state.status = 'failed';
        state.error = action.payload || 'Failed to fetch loan application';
      });
  }
});

export const { resetStatus } = loansSlice.actions;

export default loansSlice.reducer;
