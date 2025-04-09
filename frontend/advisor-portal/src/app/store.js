import { configureStore } from '@reduxjs/toolkit';
import loansReducer from '../features/loans/loansSlice';

export const store = configureStore({
  reducer: {
    loans: loansReducer,
  },
});
