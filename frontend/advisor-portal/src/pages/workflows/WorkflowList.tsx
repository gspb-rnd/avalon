import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Link } from 'react-router-dom';
import { 
  Box, 
  Typography, 
  Button, 
  Paper, 
  Table, 
  TableBody, 
  TableCell, 
  TableContainer, 
  TableHead, 
  TableRow,
  CircularProgress,
  Alert
} from '@mui/material';
import { format } from 'date-fns';
import { PageHeader } from '@avalon/shared-components';
import { WorkflowList as WorkflowListComponent } from '../../features/workflow';
import { fetchWorkflows, selectWorkflows, selectWorkflowLoading, selectWorkflowError } from '../../features/workflow/workflowSlice';

const WorkflowList: React.FC = () => {
  const dispatch = useDispatch();
  const workflows = useSelector(selectWorkflows);
  const loading = useSelector(selectWorkflowLoading);
  const error = useSelector(selectWorkflowError);

  useEffect(() => {
    dispatch(fetchWorkflows());
  }, [dispatch]);

  return (
    <Box>
      <PageHeader 
        title="Workflows" 
        subtitle="Manage and monitor all workflows"
        action={
          <Button 
            variant="contained" 
            color="primary" 
            component={Link} 
            to="/workflows/new"
          >
            Create Workflow
          </Button>
        }
      />

      {loading && (
        <Box display="flex" justifyContent="center" my={4}>
          <CircularProgress />
        </Box>
      )}

      {error && (
        <Alert severity="error" sx={{ my: 2 }}>
          {error}
        </Alert>
      )}

      {!loading && !error && (
        <WorkflowListComponent workflows={workflows} />
      )}
    </Box>
  );
};

export default WorkflowList;
