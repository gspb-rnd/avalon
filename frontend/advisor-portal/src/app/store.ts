import { configureStore } from '@reduxjs/toolkit';
import { workflowReducer } from '../features/workflow';
import { loansReducer } from '../features/loans';
import { documentsReducer } from '../features/documents';

export const store = configureStore({
  reducer: {
    workflow: workflowReducer,
    loans: loansReducer,
    documents: documentsReducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
