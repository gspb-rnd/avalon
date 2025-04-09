import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

const API_URL = 'http://Avalon-Avalo-xnqKtQR5AFrc-327551526.us-east-1.elb.amazonaws.com/api';

export const createLoanApplication = createAsyncThunk(
  'loans/createLoanApplication',
  async (loanData, { rejectWithValue }) => {
    try {
      const response = await axios.post(`${API_URL}/loan-applications`, loanData);
      return response.data;
    } catch (error) {
      return rejectWithValue(error.response.data);
    }
  }
);

export const fetchLoanApplications = createAsyncThunk(
  'loans/fetchLoanApplications',
  async (_, { rejectWithValue }) => {
    try {
      const response = await axios.get(`${API_URL}/loan-applications`);
      return response.data;
    } catch (error) {
      return rejectWithValue(error.response.data);
    }
  }
);

export const fetchLoanApplicationById = createAsyncThunk(
  'loans/fetchLoanApplicationById',
  async (id, { rejectWithValue }) => {
    try {
      const response = await axios.get(`${API_URL}/loan-applications/${id}`);
      return response.data;
    } catch (error) {
      return rejectWithValue(error.response.data);
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
