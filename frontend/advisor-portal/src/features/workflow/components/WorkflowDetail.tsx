import React, { useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { 
  fetchWorkflow, 
  selectWorkflow, 
  selectWorkflowLoading, 
  selectWorkflowError,
  completeWorkflow,
  cancelWorkflow,
  clearWorkflow
} from '../workflowSlice';
import { 
  Box, 
  Typography, 
  Paper, 
  Grid, 
  Button, 
  Divider, 
  CircularProgress,
  Card,
  CardContent,
  CardActions,
  Chip,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle
} from '@mui/material';
import { format } from 'date-fns';
import { StatusChip, PageHeader, DataCard } from '@avalon/shared-components';
import TaskList from './TaskList';

const WorkflowDetail: React.FC = () => {
  const { id } = useParams<{ id: string }>();
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const workflow = useSelector(selectWorkflow);
  const loading = useSelector(selectWorkflowLoading);
  const error = useSelector(selectWorkflowError);
  const [completeDialogOpen, setCompleteDialogOpen] = React.useState(false);
  const [cancelDialogOpen, setCancelDialogOpen] = React.useState(false);

  useEffect(() => {
    if (id) {
      dispatch(fetchWorkflow(id));
    }
    
    return () => {
      dispatch(clearWorkflow());
    };
  }, [dispatch, id]);

  const getStatusColor = (status: string) => {
    switch (status) {
      case 'IN_PROGRESS':
        return 'primary';
      case 'COMPLETED':
        return 'success';
      case 'CANCELLED':
        return 'error';
      case 'SUSPENDED':
        return 'warning';
      case 'ERROR':
        return 'error';
      default:
        return 'default';
    }
  };

  const handleCompleteWorkflow = () => {
    if (id && workflow) {
      dispatch(completeWorkflow({ 
        id, 
        completedBy: 'current-user' // In a real app, this would be the current user
      }));
      setCompleteDialogOpen(false);
    }
  };

  const handleCancelWorkflow = () => {
    if (id && workflow) {
      dispatch(cancelWorkflow({ 
        id, 
        cancelledBy: 'current-user' // In a real app, this would be the current user
      }));
      setCancelDialogOpen(false);
    }
  };

  if (loading && !workflow) {
    return (
      <Box display="flex" justifyContent="center" alignItems="center" minHeight="200px">
        <CircularProgress />
      </Box>
    );
  }

  if (error) {
    return (
      <Box>
        <Typography color="error">{error}</Typography>
        <Button 
          variant="contained" 
          onClick={() => id && dispatch(fetchWorkflow(id))}
          sx={{ mt: 2 }}
        >
          Retry
        </Button>
      </Box>
    );
  }

  if (!workflow) {
    return (
      <Box>
        <Typography>Workflow not found</Typography>
        <Button 
          variant="contained" 
          onClick={() => navigate('/workflows')}
          sx={{ mt: 2 }}
        >
          Back to Workflows
        </Button>
      </Box>
    );
  }

  const isWorkflowActive = workflow.status === 'IN_PROGRESS';

  return (
    <Box>
      <PageHeader 
        title={workflow.name}
        subtitle={`Workflow ID: ${workflow.id}`}
        actions={
          <Box>
            <Button 
              variant="outlined" 
              onClick={() => navigate('/workflows')}
              sx={{ mr: 1 }}
            >
              Back
            </Button>
            {isWorkflowActive && (
              <>
                <Button 
                  variant="contained" 
                  color="primary" 
                  onClick={() => setCompleteDialogOpen(true)}
                  sx={{ mr: 1 }}
                >
                  Complete
                </Button>
                <Button 
                  variant="contained" 
                  color="error" 
                  onClick={() => setCancelDialogOpen(true)}
                >
                  Cancel
                </Button>
              </>
            )}
          </Box>
        }
      />

      <Grid container spacing={3} sx={{ mt: 1 }}>
        <Grid item xs={12} md={6}>
          <DataCard
            title="Workflow Details"
            content={
              <Grid container spacing={2}>
                <Grid item xs={6}>
                  <Typography variant="subtitle2">Type</Typography>
                  <Typography>{workflow.type}</Typography>
                </Grid>
                <Grid item xs={6}>
                  <Typography variant="subtitle2">Status</Typography>
                  <StatusChip 
                    label={workflow.status} 
                    color={getStatusColor(workflow.status)}
                  />
                </Grid>
                <Grid item xs={6}>
                  <Typography variant="subtitle2">Created By</Typography>
                  <Typography>{workflow.createdBy}</Typography>
                </Grid>
                <Grid item xs={6}>
                  <Typography variant="subtitle2">Created At</Typography>
                  <Typography>
                    {format(new Date(workflow.createdAt), 'MMM d, yyyy HH:mm')}
                  </Typography>
                </Grid>
                <Grid item xs={6}>
                  <Typography variant="subtitle2">Updated At</Typography>
                  <Typography>
                    {format(new Date(workflow.updatedAt), 'MMM d, yyyy HH:mm')}
                  </Typography>
                </Grid>
                <Grid item xs={6}>
                  <Typography variant="subtitle2">Business Object ID</Typography>
                  <Typography>{workflow.businessObjectId}</Typography>
                </Grid>
                <Grid item xs={12}>
                  <Typography variant="subtitle2">Description</Typography>
                  <Typography>{workflow.description || 'No description'}</Typography>
                </Grid>
              </Grid>
            }
          />
        </Grid>
        <Grid item xs={12} md={6}>
          <DataCard
            title="Process Details"
            content={
              <Grid container spacing={2}>
                <Grid item xs={12}>
                  <Typography variant="subtitle2">Process Definition Key</Typography>
                  <Typography>{workflow.processDefinitionKey}</Typography>
                </Grid>
                <Grid item xs={12}>
                  <Typography variant="subtitle2">Process Instance ID</Typography>
                  <Typography>{workflow.processInstanceId}</Typography>
                </Grid>
              </Grid>
            }
          />
        </Grid>
        <Grid item xs={12}>
          <TaskList workflowId={workflow.id} tasks={workflow.tasks} />
        </Grid>
      </Grid>

      {/* Complete Workflow Dialog */}
      <Dialog
        open={completeDialogOpen}
        onClose={() => setCompleteDialogOpen(false)}
      >
        <DialogTitle>Complete Workflow</DialogTitle>
        <DialogContent>
          <DialogContentText>
            Are you sure you want to complete this workflow? This action cannot be undone.
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={() => setCompleteDialogOpen(false)}>Cancel</Button>
          <Button onClick={handleCompleteWorkflow} color="primary" variant="contained">
            Complete
          </Button>
        </DialogActions>
      </Dialog>

      {/* Cancel Workflow Dialog */}
      <Dialog
        open={cancelDialogOpen}
        onClose={() => setCancelDialogOpen(false)}
      >
        <DialogTitle>Cancel Workflow</DialogTitle>
        <DialogContent>
          <DialogContentText>
            Are you sure you want to cancel this workflow? This action cannot be undone.
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={() => setCancelDialogOpen(false)}>No</Button>
          <Button onClick={handleCancelWorkflow} color="error" variant="contained">
            Yes, Cancel
          </Button>
        </DialogActions>
      </Dialog>
    </Box>
  );
};

export default WorkflowDetail;
