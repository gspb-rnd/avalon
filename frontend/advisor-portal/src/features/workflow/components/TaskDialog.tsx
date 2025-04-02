import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import {
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  Button,
  TextField,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
  Stack
} from '@mui/material';
import { createTask, assignTask } from '../workflowSlice';

interface TaskDialogProps {
  open: boolean;
  onClose: () => void;
  workflowId: string;
  mode: 'create' | 'assign';
  taskId?: string;
}

const TaskDialog: React.FC<TaskDialogProps> = ({
  open,
  onClose,
  workflowId,
  mode,
  taskId
}) => {
  const dispatch = useDispatch();
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [assignee, setAssignee] = useState('');
  const [priority, setPriority] = useState('MEDIUM');

  const handleSubmit = () => {
    if (mode === 'create') {
      dispatch(createTask({
        workflowId,
        title,
        description,
        assignee,
        priority
      }));
    } else if (mode === 'assign' && taskId) {
      dispatch(assignTask({
        workflowId,
        taskId,
        assignee
      }));
    }
    handleClose();
  };

  const handleClose = () => {
    setTitle('');
    setDescription('');
    setAssignee('');
    setPriority('MEDIUM');
    onClose();
  };

  return (
    <Dialog open={open} onClose={handleClose} maxWidth="sm" fullWidth>
      <DialogTitle>
        {mode === 'create' ? 'Create New Task' : 'Assign Task'}
      </DialogTitle>
      <DialogContent>
        <Stack spacing={3} sx={{ mt: 2 }}>
          {mode === 'create' && (
            <>
              <TextField
                label="Title"
                value={title}
                onChange={(e) => setTitle(e.target.value)}
                fullWidth
                required
              />
              <TextField
                label="Description"
                value={description}
                onChange={(e) => setDescription(e.target.value)}
                multiline
                rows={4}
                fullWidth
              />
              <FormControl fullWidth>
                <InputLabel>Priority</InputLabel>
                <Select
                  value={priority}
                  label="Priority"
                  onChange={(e) => setPriority(e.target.value)}
                >
                  <MenuItem value="LOW">Low</MenuItem>
                  <MenuItem value="MEDIUM">Medium</MenuItem>
                  <MenuItem value="HIGH">High</MenuItem>
                </Select>
              </FormControl>
            </>
          )}
          <TextField
            label="Assignee"
            value={assignee}
            onChange={(e) => setAssignee(e.target.value)}
            fullWidth
            required
          />
        </Stack>
      </DialogContent>
      <DialogActions>
        <Button onClick={handleClose}>Cancel</Button>
        <Button 
          onClick={handleSubmit} 
          variant="contained" 
          color="primary"
          disabled={mode === 'create' ? !title || !assignee : !assignee}
        >
          {mode === 'create' ? 'Create' : 'Assign'}
        </Button>
      </DialogActions>
    </Dialog>
  );
};

export default TaskDialog;
