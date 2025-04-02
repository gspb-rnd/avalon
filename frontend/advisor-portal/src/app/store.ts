import { configureStore } from '@reduxjs/toolkit';
import clientsReducer from './features/clients/clientsSlice';
import loanApplicationsReducer from './features/loans/loanApplicationsSlice';
import collateralsReducer from './features/collaterals/collateralsSlice';
import documentsReducer from './features/documents/documentsSlice';
import workflowsReducer from './features/workflows/workflowsSlice';
import authReducer from './features/auth/authSlice';

export const store = configureStore({
  reducer: {
    clients: clientsReducer,
    loanApplications: loanApplicationsReducer,
    collaterals: collateralsReducer,
    documents: documentsReducer,
    workflows: workflowsReducer,
    auth: authReducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
