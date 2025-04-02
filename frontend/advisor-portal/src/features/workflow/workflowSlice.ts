import { createSlice, createAsyncThunk, PayloadAction } from '@reduxjs/toolkit';
import { RootState } from '../../app/store';
import axios from 'axios';

export interface Task {
  id: string;
  workflowId: string;
  name: string;
  description: string;
  assignee: string;
  status: string;
  priority: string;
  createdAt: string;
  updatedAt: string;
  dueDate: string | null;
  completedAt: string | null;
  completionNotes: string | null;
  formKey: string | null;
  taskDefinitionKey: string | null;
  isOverdue: boolean;
}

export interface Workflow {
  id: string;
  type: string;
  businessObjectId: string;
  name: string;
  description: string;
  createdBy: string;
  createdAt: string;
  updatedAt: string;
  status: string;
  processInstanceId: string;
  processDefinitionKey: string;
  tasks: Task[];
}

interface WorkflowState {
  workflows: Workflow[];
  workflow: Workflow | null;
  loading: boolean;
  error: string | null;
}

const initialState: WorkflowState = {
  workflows: [],
  workflow: null,
  loading: false,
  error: null,
};

export const fetchWorkflows = createAsyncThunk(
  'workflow/fetchWorkflows',
  async (_, { rejectWithValue }) => {
    try {
      const response = await axios.get('/api/workflows');
      return response.data;
    } catch (error: any) {
      return rejectWithValue(error.response?.data?.message || 'Failed to fetch workflows');
    }
  }
);

export const fetchWorkflowsByBusinessObject = createAsyncThunk(
  'workflow/fetchWorkflowsByBusinessObject',
  async (businessObjectId: string, { rejectWithValue }) => {
    try {
      const response = await axios.get(`/api/workflows/business-object/${businessObjectId}`);
      return response.data;
    } catch (error: any) {
      return rejectWithValue(error.response?.data?.message || 'Failed to fetch workflows');
    }
  }
);

export const fetchWorkflow = createAsyncThunk(
  'workflow/fetchWorkflow',
  async (id: string, { rejectWithValue }) => {
    try {
      const response = await axios.get(`/api/workflows/${id}`);
      return response.data;
    } catch (error: any) {
      return rejectWithValue(error.response?.data?.message || 'Failed to fetch workflow');
    }
  }
);

export const createWorkflow = createAsyncThunk(
  'workflow/createWorkflow',
  async (workflow: any, { rejectWithValue }) => {
    try {
      const response = await axios.post('/api/workflows', workflow);
      return response.data;
    } catch (error: any) {
      return rejectWithValue(error.response?.data?.message || 'Failed to create workflow');
    }
  }
);

export const createTask = createAsyncThunk(
  'workflow/createTask',
  async (task: any, { rejectWithValue }) => {
    try {
      const response = await axios.post('/api/workflows/tasks', task);
      return response.data;
    } catch (error: any) {
      return rejectWithValue(error.response?.data?.message || 'Failed to create task');
    }
  }
);

export const assignTask = createAsyncThunk(
  'workflow/assignTask',
  async (assignTaskCommand: any, { rejectWithValue }) => {
    try {
      const response = await axios.put('/api/workflows/tasks/assign', assignTaskCommand);
      return response.data;
    } catch (error: any) {
      return rejectWithValue(error.response?.data?.message || 'Failed to assign task');
    }
  }
);

export const completeTask = createAsyncThunk(
  'workflow/completeTask',
  async (completeTaskCommand: any, { rejectWithValue }) => {
    try {
      const response = await axios.put('/api/workflows/tasks/complete', completeTaskCommand);
      return response.data;
    } catch (error: any) {
      return rejectWithValue(error.response?.data?.message || 'Failed to complete task');
    }
  }
);

export const completeWorkflow = createAsyncThunk(
  'workflow/completeWorkflow',
  async ({ id, completedBy }: { id: string; completedBy: string }, { rejectWithValue }) => {
    try {
      const response = await axios.put(`/api/workflows/${id}/complete?completedBy=${completedBy}`);
      return response.data;
    } catch (error: any) {
      return rejectWithValue(error.response?.data?.message || 'Failed to complete workflow');
    }
  }
);

export const cancelWorkflow = createAsyncThunk(
  'workflow/cancelWorkflow',
  async ({ id, cancelledBy }: { id: string; cancelledBy: string }, { rejectWithValue }) => {
    try {
      const response = await axios.put(`/api/workflows/${id}/cancel?cancelledBy=${cancelledBy}`);
      return response.data;
    } catch (error: any) {
      return rejectWithValue(error.response?.data?.message || 'Failed to cancel workflow');
    }
  }
);

const workflowSlice = createSlice({
  name: 'workflow',
  initialState,
  reducers: {
    clearWorkflow: (state) => {
      state.workflow = null;
    },
    clearError: (state) => {
      state.error = null;
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(fetchWorkflows.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(fetchWorkflows.fulfilled, (state, action: PayloadAction<Workflow[]>) => {
        state.loading = false;
        state.workflows = action.payload;
      })
      .addCase(fetchWorkflows.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload as string;
      })
      .addCase(fetchWorkflowsByBusinessObject.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(fetchWorkflowsByBusinessObject.fulfilled, (state, action: PayloadAction<Workflow[]>) => {
        state.loading = false;
        state.workflows = action.payload;
      })
      .addCase(fetchWorkflowsByBusinessObject.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload as string;
      })
      .addCase(fetchWorkflow.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(fetchWorkflow.fulfilled, (state, action: PayloadAction<Workflow>) => {
        state.loading = false;
        state.workflow = action.payload;
      })
      .addCase(fetchWorkflow.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload as string;
      })
      .addCase(createWorkflow.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(createWorkflow.fulfilled, (state, action: PayloadAction<Workflow>) => {
        state.loading = false;
        state.workflows.push(action.payload);
        state.workflow = action.payload;
      })
      .addCase(createWorkflow.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload as string;
      })
      .addCase(createTask.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(createTask.fulfilled, (state, action: PayloadAction<Workflow>) => {
        state.loading = false;
        state.workflow = action.payload;
        const index = state.workflows.findIndex(w => w.id === action.payload.id);
        if (index !== -1) {
          state.workflows[index] = action.payload;
        }
      })
      .addCase(createTask.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload as string;
      })
      .addCase(assignTask.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(assignTask.fulfilled, (state, action: PayloadAction<Workflow>) => {
        state.loading = false;
        state.workflow = action.payload;
        const index = state.workflows.findIndex(w => w.id === action.payload.id);
        if (index !== -1) {
          state.workflows[index] = action.payload;
        }
      })
      .addCase(assignTask.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload as string;
      })
      .addCase(completeTask.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(completeTask.fulfilled, (state, action: PayloadAction<Workflow>) => {
        state.loading = false;
        state.workflow = action.payload;
        const index = state.workflows.findIndex(w => w.id === action.payload.id);
        if (index !== -1) {
          state.workflows[index] = action.payload;
        }
      })
      .addCase(completeTask.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload as string;
      })
      .addCase(completeWorkflow.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(completeWorkflow.fulfilled, (state, action: PayloadAction<Workflow>) => {
        state.loading = false;
        state.workflow = action.payload;
        const index = state.workflows.findIndex(w => w.id === action.payload.id);
        if (index !== -1) {
          state.workflows[index] = action.payload;
        }
      })
      .addCase(completeWorkflow.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload as string;
      })
      .addCase(cancelWorkflow.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(cancelWorkflow.fulfilled, (state, action: PayloadAction<Workflow>) => {
        state.loading = false;
        state.workflow = action.payload;
        const index = state.workflows.findIndex(w => w.id === action.payload.id);
        if (index !== -1) {
          state.workflows[index] = action.payload;
        }
      })
      .addCase(cancelWorkflow.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload as string;
      });
  },
});

export const { clearWorkflow, clearError } = workflowSlice.actions;

export const selectWorkflows = (state: RootState) => state.workflow.workflows;
export const selectWorkflow = (state: RootState) => state.workflow.workflow;
export const selectWorkflowLoading = (state: RootState) => state.workflow.loading;
export const selectWorkflowError = (state: RootState) => state.workflow.error;

export default workflowSlice.reducer;
