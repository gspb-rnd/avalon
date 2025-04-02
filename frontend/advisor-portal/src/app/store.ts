import { configureStore } from '@reduxjs/toolkit';
import { workflowReducer } from '../features/workflow';
import { loansReducer } from '../features/loans';

export const store = configureStore({
  reducer: {
    workflow: workflowReducer,
    loans: loansReducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
