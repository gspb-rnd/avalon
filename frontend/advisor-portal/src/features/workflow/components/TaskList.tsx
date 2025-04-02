import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
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
  IconButton,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  TextField,
  MenuItem,
  Select,
  FormControl,
  InputLabel,
  Grid
} from '@mui/material';
import { format } from 'date-fns';
import { StatusChip } from '@avalon/shared-components';
import { Task, assignTask, completeTask, createTask } from '../workflowSlice';

interface TaskListProps {
  workflowId: string;
  tasks: Task[];
}

const TaskList: React.FC<TaskListProps> = ({ workflowId, tasks }) => {
  const dispatch = useDispatch();
  const [createDialogOpen, setCreateDialogOpen] = useState(false);
  const [assignDialogOpen, setAssignDialogOpen] = useState(false);
  const [completeDialogOpen, setCompleteDialogOpen] = useState(false);
  const [selectedTask, setSelectedTask] = useState<Task | null>(null);
  
  const [newTaskName, setNewTaskName] = useState('');
  const [newTaskDescription, setNewTaskDescription] = useState('');
  const [newTaskAssignee, setNewTaskAssignee] = useState('');
  const [newTaskPriority, setNewTaskPriority] = useState('MEDIUM');
  
  const [assigneeInput, setAssigneeInput] = useState('');
  
  const [completionNotes, setCompletionNotes] = useState('');

  const getStatusColor = (status: string) => {
    switch (status) {
      case 'PENDING':
        return 'default';
      case 'IN_PROGRESS':
        return 'primary';
      case 'COMPLETED':
        return 'success';
      case 'CANCELLED':
        return 'error';
      default:
        return 'default';
    }
  };

  const getPriorityColor = (priority: string) => {
    switch (priority) {
      case 'HIGH':
        return 'error';
      case 'MEDIUM':
        return 'warning';
      case 'LOW':
        return 'info';
      default:
        return 'default';
    }
  };

  const handleCreateTask = () => {
    dispatch(createTask({
      workflowId,
      name: newTaskName,
      description: newTaskDescription,
      assignee: newTaskAssignee || null,
      priority: newTaskPriority,
      dueDate: null, // In a real app, you would add a date picker
      formKey: null,
      taskDefinitionKey: null
    }));
    
    setNewTaskName('');
    setNewTaskDescription('');
    setNewTaskAssignee('');
    setNewTaskPriority('MEDIUM');
    setCreateDialogOpen(false);
  };

  const handleAssignTask = () => {
    if (selectedTask) {
      dispatch(assignTask({
        taskId: selectedTask.id,
        assignee: assigneeInput,
        assignedBy: 'current-user' // In a real app, this would be the current user
      }));
      
      setAssigneeInput('');
      setSelectedTask(null);
      setAssignDialogOpen(false);
    }
  };

  const handleCompleteTask = () => {
    if (selectedTask) {
      dispatch(completeTask({
        taskId: selectedTask.id,
        completionNotes,
        completedBy: 'current-user' // In a real app, this would be the current user
      }));
      
      setCompletionNotes('');
      setSelectedTask(null);
      setCompleteDialogOpen(false);
    }
  };

  const openAssignDialog = (task: Task) => {
    setSelectedTask(task);
    setAssigneeInput(task.assignee || '');
    setAssignDialogOpen(true);
  };

  const openCompleteDialog = (task: Task) => {
    setSelectedTask(task);
    setCompleteDialogOpen(true);
  };

  return (
    <Box>
      <Box display="flex" justifyContent="space-between" alignItems="center" mb={2}>
        <Typography variant="h6">Tasks</Typography>
        <Button 
          variant="contained" 
          color="primary" 
          onClick={() => setCreateDialogOpen(true)}
        >
          Create Task
        </Button>
      </Box>

      {tasks.length === 0 ? (
        <Paper sx={{ p: 3, textAlign: 'center' }}>
          <Typography>No tasks found</Typography>
        </Paper>
      ) : (
        <TableContainer component={Paper}>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell>Name</TableCell>
                <TableCell>Description</TableCell>
                <TableCell>Assignee</TableCell>
                <TableCell>Status</TableCell>
                <TableCell>Priority</TableCell>
                <TableCell>Created At</TableCell>
                <TableCell>Due Date</TableCell>
                <TableCell>Actions</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {tasks.map((task) => (
                <TableRow key={task.id}>
                  <TableCell>{task.name}</TableCell>
                  <TableCell>{task.description || 'No description'}</TableCell>
                  <TableCell>{task.assignee || 'Unassigned'}</TableCell>
                  <TableCell>
                    <StatusChip 
                      label={task.status} 
                      color={getStatusColor(task.status)}
                    />
                    {task.isOverdue && (
                      <StatusChip 
                        label="OVERDUE" 
                        color="error"
                        sx={{ ml: 1 }}
                      />
                    )}
                  </TableCell>
                  <TableCell>
                    <StatusChip 
                      label={task.priority} 
                      color={getPriorityColor(task.priority)}
                    />
                  </TableCell>
                  <TableCell>
                    {format(new Date(task.createdAt), 'MMM d, yyyy HH:mm')}
                  </TableCell>
                  <TableCell>
                    {task.dueDate ? format(new Date(task.dueDate), 'MMM d, yyyy') : 'No due date'}
                  </TableCell>
                  <TableCell>
                    {task.status !== 'COMPLETED' && task.status !== 'CANCELLED' && (
                      <>
                        <Button 
                          variant="outlined" 
                          size="small" 
                          onClick={() => openAssignDialog(task)}
                          sx={{ mr: 1 }}
                        >
                          Assign
                        </Button>
                        <Button 
                          variant="contained" 
                          size="small" 
                          color="primary"
                          onClick={() => openCompleteDialog(task)}
                        >
                          Complete
                        </Button>
                      </>
                    )}
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      )}

      {/* Create Task Dialog */}
      <Dialog
        open={createDialogOpen}
        onClose={() => setCreateDialogOpen(false)}
        maxWidth="md"
        fullWidth
      >
        <DialogTitle>Create New Task</DialogTitle>
        <DialogContent>
          <Grid container spacing={2} sx={{ mt: 1 }}>
            <Grid item xs={12}>
              <TextField
                label="Name"
                value={newTaskName}
                onChange={(e) => setNewTaskName(e.target.value)}
                fullWidth
                required
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                label="Description"
                value={newTaskDescription}
                onChange={(e) => setNewTaskDescription(e.target.value)}
                fullWidth
                multiline
                rows={3}
              />
            </Grid>
            <Grid item xs={12} md={6}>
              <TextField
                label="Assignee"
                value={newTaskAssignee}
                onChange={(e) => setNewTaskAssignee(e.target.value)}
                fullWidth
                placeholder="Leave blank to create unassigned"
              />
            </Grid>
            <Grid item xs={12} md={6}>
              <FormControl fullWidth>
                <InputLabel>Priority</InputLabel>
                <Select
                  value={newTaskPriority}
                  onChange={(e) => setNewTaskPriority(e.target.value)}
                  label="Priority"
                >
                  <MenuItem value="HIGH">High</MenuItem>
                  <MenuItem value="MEDIUM">Medium</MenuItem>
                  <MenuItem value="LOW">Low</MenuItem>
                </Select>
              </FormControl>
            </Grid>
          </Grid>
        </DialogContent>
        <DialogActions>
          <Button onClick={() => setCreateDialogOpen(false)}>Cancel</Button>
          <Button 
            onClick={handleCreateTask} 
            color="primary" 
            variant="contained"
            disabled={!newTaskName}
          >
            Create
          </Button>
        </DialogActions>
      </Dialog>

      {/* Assign Task Dialog */}
      <Dialog
        open={assignDialogOpen}
        onClose={() => setAssignDialogOpen(false)}
      >
        <DialogTitle>Assign Task</DialogTitle>
        <DialogContent>
          <DialogContentText>
            Assign task "{selectedTask?.name}" to:
          </DialogContentText>
          <TextField
            autoFocus
            margin="dense"
            label="Assignee"
            value={assigneeInput}
            onChange={(e) => setAssigneeInput(e.target.value)}
            fullWidth
            required
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={() => setAssignDialogOpen(false)}>Cancel</Button>
          <Button 
            onClick={handleAssignTask} 
            color="primary" 
            variant="contained"
            disabled={!assigneeInput}
          >
            Assign
          </Button>
        </DialogActions>
      </Dialog>

      {/* Complete Task Dialog */}
      <Dialog
        open={completeDialogOpen}
        onClose={() => setCompleteDialogOpen(false)}
      >
        <DialogTitle>Complete Task</DialogTitle>
        <DialogContent>
          <DialogContentText>
            Complete task "{selectedTask?.name}" with the following notes:
          </DialogContentText>
          <TextField
            autoFocus
            margin="dense"
            label="Completion Notes"
            value={completionNotes}
            onChange={(e) => setCompletionNotes(e.target.value)}
            fullWidth
            multiline
            rows={4}
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={() => setCompleteDialogOpen(false)}>Cancel</Button>
          <Button 
            onClick={handleCompleteTask} 
            color="primary" 
            variant="contained"
          >
            Complete
          </Button>
        </DialogActions>
      </Dialog>
    </Box>
  );
};

export default TaskList;
