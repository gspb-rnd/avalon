import { createSlice, createAsyncThunk, PayloadAction } from '@reduxjs/toolkit';
import { RootState } from '../../app/store';
import axios from 'axios';

export interface Collateral {
  id: string;
  loanApplicationId: string;
  type: string;
  description: string;
  estimatedValue: number;
  appraisedValue: number | null;
  appraisalDate: string | null;
  status: string;
  documents: string[];
  createdAt: string;
  updatedAt: string;
}

export interface LoanTerms {
  amount: number;
  currency: string;
  interestRate: number;
  interestRateType: string;
  term: number;
  termUnit: string;
  paymentFrequency: string;
  startDate: string | null;
  endDate: string | null;
}

export interface ValidationRule {
  id: string;
  name: string;
  description: string;
  result: boolean;
  severity: string;
  message: string;
  overridden: boolean;
  overriddenBy: string | null;
  overrideReason: string | null;
  overrideDate: string | null;
}

export interface LoanApplication {
  id: string;
  clientId: string;
  clientName: string;
  type: string;
  purpose: string;
  status: string;
  terms: LoanTerms;
  collaterals: Collateral[];
  validationRules: ValidationRule[];
  createdBy: string;
  createdAt: string;
  updatedAt: string;
  submittedAt: string | null;
  approvedAt: string | null;
  rejectedAt: string | null;
  rejectionReason: string | null;
  notes: string | null;
}

interface LoansState {
  loanApplications: LoanApplication[];
  loanApplication: LoanApplication | null;
  loading: boolean;
  error: string | null;
}

const initialState: LoansState = {
  loanApplications: [],
  loanApplication: null,
  loading: false,
  error: null,
};

export const fetchLoanApplications = createAsyncThunk(
  'loans/fetchLoanApplications',
  async (_, { rejectWithValue }) => {
    try {
      const response = await axios.get('/api/loan-applications');
      return response.data;
    } catch (error: any) {
      return rejectWithValue(error.response?.data?.message || 'Failed to fetch loan applications');
    }
  }
);

export const fetchLoanApplicationsByClient = createAsyncThunk(
  'loans/fetchLoanApplicationsByClient',
  async (clientId: string, { rejectWithValue }) => {
    try {
      const response = await axios.get(`/api/loan-applications/client/${clientId}`);
      return response.data;
    } catch (error: any) {
      return rejectWithValue(error.response?.data?.message || 'Failed to fetch loan applications');
    }
  }
);

export const fetchLoanApplication = createAsyncThunk(
  'loans/fetchLoanApplication',
  async (id: string, { rejectWithValue }) => {
    try {
      const response = await axios.get(`/api/loan-applications/${id}`);
      return response.data;
    } catch (error: any) {
      return rejectWithValue(error.response?.data?.message || 'Failed to fetch loan application');
    }
  }
);

export const createLoanApplication = createAsyncThunk(
  'loans/createLoanApplication',
  async (loanApplication: any, { rejectWithValue }) => {
    try {
      const response = await axios.post('/api/loan-applications', loanApplication);
      return response.data;
    } catch (error: any) {
      return rejectWithValue(error.response?.data?.message || 'Failed to create loan application');
    }
  }
);

export const updateLoanApplication = createAsyncThunk(
  'loans/updateLoanApplication',
  async ({ id, loanApplication }: { id: string; loanApplication: any }, { rejectWithValue }) => {
    try {
      const response = await axios.put(`/api/loan-applications/${id}`, loanApplication);
      return response.data;
    } catch (error: any) {
      return rejectWithValue(error.response?.data?.message || 'Failed to update loan application');
    }
  }
);

export const submitLoanApplication = createAsyncThunk(
  'loans/submitLoanApplication',
  async (id: string, { rejectWithValue }) => {
    try {
      const response = await axios.put(`/api/loan-applications/${id}/submit`);
      return response.data;
    } catch (error: any) {
      return rejectWithValue(error.response?.data?.message || 'Failed to submit loan application');
    }
  }
);

export const addCollateral = createAsyncThunk(
  'loans/addCollateral',
  async ({ loanApplicationId, collateral }: { loanApplicationId: string; collateral: any }, { rejectWithValue }) => {
    try {
      const response = await axios.post(`/api/loan-applications/${loanApplicationId}/collaterals`, collateral);
      return response.data;
    } catch (error: any) {
      return rejectWithValue(error.response?.data?.message || 'Failed to add collateral');
    }
  }
);

export const updateCollateral = createAsyncThunk(
  'loans/updateCollateral',
  async ({ loanApplicationId, collateralId, collateral }: { loanApplicationId: string; collateralId: string; collateral: any }, { rejectWithValue }) => {
    try {
      const response = await axios.put(`/api/loan-applications/${loanApplicationId}/collaterals/${collateralId}`, collateral);
      return response.data;
    } catch (error: any) {
      return rejectWithValue(error.response?.data?.message || 'Failed to update collateral');
    }
  }
);

export const removeCollateral = createAsyncThunk(
  'loans/removeCollateral',
  async ({ loanApplicationId, collateralId }: { loanApplicationId: string; collateralId: string }, { rejectWithValue }) => {
    try {
      const response = await axios.delete(`/api/loan-applications/${loanApplicationId}/collaterals/${collateralId}`);
      return response.data;
    } catch (error: any) {
      return rejectWithValue(error.response?.data?.message || 'Failed to remove collateral');
    }
  }
);

export const overrideValidationRule = createAsyncThunk(
  'loans/overrideValidationRule',
  async ({ loanApplicationId, ruleId, overrideReason }: { loanApplicationId: string; ruleId: string; overrideReason: string }, { rejectWithValue }) => {
    try {
      const response = await axios.put(`/api/loan-applications/${loanApplicationId}/validation-rules/${ruleId}/override`, { overrideReason });
      return response.data;
    } catch (error: any) {
      return rejectWithValue(error.response?.data?.message || 'Failed to override validation rule');
    }
  }
);

const loansSlice = createSlice({
  name: 'loans',
  initialState,
  reducers: {
    clearLoanApplication: (state) => {
      state.loanApplication = null;
    },
    clearError: (state) => {
      state.error = null;
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(fetchLoanApplications.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(fetchLoanApplications.fulfilled, (state, action: PayloadAction<LoanApplication[]>) => {
        state.loading = false;
        state.loanApplications = action.payload;
      })
      .addCase(fetchLoanApplications.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload as string;
      })
      .addCase(fetchLoanApplicationsByClient.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(fetchLoanApplicationsByClient.fulfilled, (state, action: PayloadAction<LoanApplication[]>) => {
        state.loading = false;
        state.loanApplications = action.payload;
      })
      .addCase(fetchLoanApplicationsByClient.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload as string;
      })
      .addCase(fetchLoanApplication.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(fetchLoanApplication.fulfilled, (state, action: PayloadAction<LoanApplication>) => {
        state.loading = false;
        state.loanApplication = action.payload;
      })
      .addCase(fetchLoanApplication.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload as string;
      })
      .addCase(createLoanApplication.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(createLoanApplication.fulfilled, (state, action: PayloadAction<LoanApplication>) => {
        state.loading = false;
        state.loanApplications.push(action.payload);
        state.loanApplication = action.payload;
      })
      .addCase(createLoanApplication.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload as string;
      })
      .addCase(updateLoanApplication.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(updateLoanApplication.fulfilled, (state, action: PayloadAction<LoanApplication>) => {
        state.loading = false;
        state.loanApplication = action.payload;
        const index = state.loanApplications.findIndex(la => la.id === action.payload.id);
        if (index !== -1) {
          state.loanApplications[index] = action.payload;
        }
      })
      .addCase(updateLoanApplication.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload as string;
      })
      .addCase(submitLoanApplication.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(submitLoanApplication.fulfilled, (state, action: PayloadAction<LoanApplication>) => {
        state.loading = false;
        state.loanApplication = action.payload;
        const index = state.loanApplications.findIndex(la => la.id === action.payload.id);
        if (index !== -1) {
          state.loanApplications[index] = action.payload;
        }
      })
      .addCase(submitLoanApplication.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload as string;
      })
      .addCase(addCollateral.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(addCollateral.fulfilled, (state, action: PayloadAction<LoanApplication>) => {
        state.loading = false;
        state.loanApplication = action.payload;
        const index = state.loanApplications.findIndex(la => la.id === action.payload.id);
        if (index !== -1) {
          state.loanApplications[index] = action.payload;
        }
      })
      .addCase(addCollateral.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload as string;
      })
      .addCase(updateCollateral.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(updateCollateral.fulfilled, (state, action: PayloadAction<LoanApplication>) => {
        state.loading = false;
        state.loanApplication = action.payload;
        const index = state.loanApplications.findIndex(la => la.id === action.payload.id);
        if (index !== -1) {
          state.loanApplications[index] = action.payload;
        }
      })
      .addCase(updateCollateral.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload as string;
      })
      .addCase(removeCollateral.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(removeCollateral.fulfilled, (state, action: PayloadAction<LoanApplication>) => {
        state.loading = false;
        state.loanApplication = action.payload;
        const index = state.loanApplications.findIndex(la => la.id === action.payload.id);
        if (index !== -1) {
          state.loanApplications[index] = action.payload;
        }
      })
      .addCase(removeCollateral.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload as string;
      })
      .addCase(overrideValidationRule.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(overrideValidationRule.fulfilled, (state, action: PayloadAction<LoanApplication>) => {
        state.loading = false;
        state.loanApplication = action.payload;
        const index = state.loanApplications.findIndex(la => la.id === action.payload.id);
        if (index !== -1) {
          state.loanApplications[index] = action.payload;
        }
      })
      .addCase(overrideValidationRule.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload as string;
      });
  },
});

export const { clearLoanApplication, clearError } = loansSlice.actions;

export const selectLoanApplications = (state: RootState) => state.loans.loanApplications;
export const selectLoanApplication = (state: RootState) => state.loans.loanApplication;
export const selectLoansLoading = (state: RootState) => state.loans.loading;
export const selectLoansError = (state: RootState) => state.loans.error;

export default loansSlice.reducer;
