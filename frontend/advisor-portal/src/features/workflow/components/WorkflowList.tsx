import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { 
  fetchWorkflows, 
  selectWorkflows, 
  selectWorkflowLoading, 
  selectWorkflowError 
} from '../workflowSlice';
import { 
  Box, 
  Typography, 
  Paper, 
  Table, 
  TableBody, 
  TableCell, 
  TableContainer, 
  TableHead, 
  TableRow,
  Button,
  Chip,
  CircularProgress
} from '@mui/material';
import { Link as RouterLink } from 'react-router-dom';
import { format } from 'date-fns';
import { StatusChip } from '@avalon/shared-components';

const WorkflowList: React.FC = () => {
  const dispatch = useDispatch();
  const workflows = useSelector(selectWorkflows);
  const loading = useSelector(selectWorkflowLoading);
  const error = useSelector(selectWorkflowError);

  useEffect(() => {
    dispatch(fetchWorkflows());
  }, [dispatch]);

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

  if (loading) {
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
          onClick={() => dispatch(fetchWorkflows())}
          sx={{ mt: 2 }}
        >
          Retry
        </Button>
      </Box>
    );
  }

  return (
    <Box>
      <Box display="flex" justifyContent="space-between" alignItems="center" mb={3}>
        <Typography variant="h5" component="h2">Workflows</Typography>
        <Button 
          variant="contained" 
          color="primary" 
          component={RouterLink} 
          to="/workflows/new"
        >
          Create Workflow
        </Button>
      </Box>

      {workflows.length === 0 ? (
        <Paper sx={{ p: 3, textAlign: 'center' }}>
          <Typography>No workflows found</Typography>
        </Paper>
      ) : (
        <TableContainer component={Paper}>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell>Name</TableCell>
                <TableCell>Type</TableCell>
                <TableCell>Status</TableCell>
                <TableCell>Created By</TableCell>
                <TableCell>Created At</TableCell>
                <TableCell>Tasks</TableCell>
                <TableCell>Actions</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {workflows.map((workflow) => (
                <TableRow key={workflow.id}>
                  <TableCell>{workflow.name}</TableCell>
                  <TableCell>{workflow.type}</TableCell>
                  <TableCell>
                    <StatusChip 
                      label={workflow.status} 
                      color={getStatusColor(workflow.status)}
                    />
                  </TableCell>
                  <TableCell>{workflow.createdBy}</TableCell>
                  <TableCell>
                    {format(new Date(workflow.createdAt), 'MMM d, yyyy HH:mm')}
                  </TableCell>
                  <TableCell>
                    <Chip 
                      label={`${workflow.tasks.length} tasks`} 
                      size="small" 
                      color="primary" 
                      variant="outlined"
                    />
                  </TableCell>
                  <TableCell>
                    <Button 
                      variant="outlined" 
                      size="small" 
                      component={RouterLink} 
                      to={`/workflows/${workflow.id}`}
                    >
                      View
                    </Button>
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      )}
    </Box>
  );
};

export default WorkflowList;
