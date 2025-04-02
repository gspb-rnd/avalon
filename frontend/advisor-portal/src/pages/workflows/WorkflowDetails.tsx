import React, { useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { 
  Box, 
  Typography, 
  Button, 
  Paper, 
  Grid, 
  CircularProgress, 
  Alert,
  Divider,
  Chip,
  Stack
} from '@mui/material';
import { format } from 'date-fns';
import { PageHeader, StatusChip } from '@avalon/shared-components';
import { 
  WorkflowDetail as WorkflowDetailComponent,
  TaskList
} from '../../features/workflow';
import { 
  fetchWorkflow, 
  selectWorkflow, 
  selectWorkflowLoading, 
  selectWorkflowError,
  completeWorkflow,
  cancelWorkflow
} from '../../features/workflow/workflowSlice';

const WorkflowDetails: React.FC = () => {
  const { id } = useParams<{ id: string }>();
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const workflow = useSelector(selectWorkflow);
  const loading = useSelector(selectWorkflowLoading);
  const error = useSelector(selectWorkflowError);

  useEffect(() => {
    if (id) {
      dispatch(fetchWorkflow(id));
    }
  }, [dispatch, id]);

  const handleCompleteWorkflow = () => {
    if (workflow && id) {
      dispatch(completeWorkflow({ 
        id, 
        completedBy: 'current-user' // In a real app, this would be the current user
      }));
    }
  };

  const handleCancelWorkflow = () => {
    if (workflow && id) {
      dispatch(cancelWorkflow({ 
        id, 
        cancelledBy: 'current-user' // In a real app, this would be the current user
      }));
    }
  };

  const getStatusColor = (status: string) => {
    switch (status) {
      case 'PENDING':
        return 'warning';
      case 'IN_PROGRESS':
        return 'info';
      case 'COMPLETED':
        return 'success';
      case 'FAILED':
        return 'error';
      case 'CANCELLED':
        return 'default';
      default:
        return 'default';
    }
  };

  return (
    <Box>
      <PageHeader 
        title="Workflow Details" 
        subtitle={workflow ? `${workflow.name} (${workflow.type})` : 'Loading...'}
        action={
          <Button 
            variant="outlined" 
            color="primary" 
            onClick={() => navigate('/workflows')}
          >
            Back to Workflows
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

      {!loading && !error && workflow && (
        <>
          <Paper sx={{ p: 3, mb: 3 }}>
            <Grid container spacing={3}>
              <Grid item xs={12} md={6}>
                <Typography variant="subtitle2" color="text.secondary">
                  Workflow ID
                </Typography>
                <Typography variant="body1">
                  {workflow.id}
                </Typography>
              </Grid>
              <Grid item xs={12} md={6}>
                <Typography variant="subtitle2" color="text.secondary">
                  Business Object ID
                </Typography>
                <Typography variant="body1">
                  {workflow.businessObjectId}
                </Typography>
              </Grid>
              <Grid item xs={12} md={6}>
                <Typography variant="subtitle2" color="text.secondary">
                  Status
                </Typography>
                <StatusChip 
                  label={workflow.status} 
                  color={getStatusColor(workflow.status)}
                />
              </Grid>
              <Grid item xs={12} md={6}>
                <Typography variant="subtitle2" color="text.secondary">
                  Created By
                </Typography>
                <Typography variant="body1">
                  {workflow.createdBy}
                </Typography>
              </Grid>
              <Grid item xs={12} md={6}>
                <Typography variant="subtitle2" color="text.secondary">
                  Created At
                </Typography>
                <Typography variant="body1">
                  {format(new Date(workflow.createdAt), 'MMM d, yyyy HH:mm:ss')}
                </Typography>
              </Grid>
              <Grid item xs={12} md={6}>
                <Typography variant="subtitle2" color="text.secondary">
                  Updated At
                </Typography>
                <Typography variant="body1">
                  {format(new Date(workflow.updatedAt), 'MMM d, yyyy HH:mm:ss')}
                </Typography>
              </Grid>
              <Grid item xs={12}>
                <Typography variant="subtitle2" color="text.secondary">
                  Description
                </Typography>
                <Typography variant="body1">
                  {workflow.description || 'No description provided'}
                </Typography>
              </Grid>
              <Grid item xs={12}>
                <Divider sx={{ my: 2 }} />
                <Stack direction="row" spacing={2}>
                  {workflow.status !== 'COMPLETED' && workflow.status !== 'CANCELLED' && (
                    <>
                      <Button 
                        variant="contained" 
                        color="primary" 
                        onClick={handleCompleteWorkflow}
                      >
                        Complete Workflow
                      </Button>
                      <Button 
                        variant="outlined" 
                        color="error" 
                        onClick={handleCancelWorkflow}
                      >
                        Cancel Workflow
                      </Button>
                    </>
                  )}
                </Stack>
              </Grid>
            </Grid>
          </Paper>

          <Box mb={3}>
            <TaskList workflowId={workflow.id} tasks={workflow.tasks} />
          </Box>
        </>
      )}
    </Box>
  );
};

export default WorkflowDetails;
