import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import {
  Box,
  Card,
  CardContent,
  Typography,
  Button,
  Stack,
  IconButton,
  Tooltip
} from '@mui/material';
import {
  Assignment as AssignmentIcon,
  CheckCircle as CheckCircleIcon,
  Person as PersonIcon
} from '@mui/icons-material';
import { format } from 'date-fns';
import { StatusChip } from '@avalon/shared-components';
import { completeTask } from '../workflowSlice';
import TaskDialog from './TaskDialog';

interface TaskItemProps {
  workflowId: string;
  task: {
    id: string;
    title: string;
    description: string;
    status: string;
    priority: string;
    assignee?: string;
    createdAt: string;
    updatedAt: string;
  };
}

const TaskItem: React.FC<TaskItemProps> = ({ workflowId, task }) => {
  const dispatch = useDispatch();
  const [assignDialogOpen, setAssignDialogOpen] = useState(false);

  const handleComplete = () => {
    dispatch(completeTask({
      workflowId,
      taskId: task.id,
      completedBy: 'current-user' // In a real app, this would be the current user
    }));
  };

  return (
    <>
      <Card variant="outlined" sx={{ mb: 2 }}>
        <CardContent>
          <Box display="flex" justifyContent="space-between" alignItems="flex-start">
            <Stack spacing={1}>
              <Typography variant="h6" component="div">
                {task.title}
              </Typography>
              <Typography variant="body2" color="text.secondary">
                {task.description}
              </Typography>
              <Box display="flex" alignItems="center" gap={1}>
                <StatusChip 
                  label={task.status} 
                  type="workflow"
                />
                <Typography variant="caption" color="text.secondary">
                  Priority: {task.priority}
                </Typography>
                {task.assignee && (
                  <Box display="flex" alignItems="center" gap={0.5}>
                    <PersonIcon fontSize="small" color="action" />
                    <Typography variant="caption">
                      {task.assignee}
                    </Typography>
                  </Box>
                )}
              </Box>
              <Typography variant="caption" color="text.secondary">
                Created: {format(new Date(task.createdAt), 'MMM d, yyyy HH:mm')}
                {task.updatedAt !== task.createdAt && 
                  ` • Updated: ${format(new Date(task.updatedAt), 'MMM d, yyyy HH:mm')}`
                }
              </Typography>
            </Stack>
            <Stack direction="row" spacing={1}>
              <Tooltip title="Assign Task">
                <IconButton 
                  size="small" 
                  onClick={() => setAssignDialogOpen(true)}
                  disabled={task.status === 'COMPLETED'}
                >
                  <AssignmentIcon />
                </IconButton>
              </Tooltip>
              <Tooltip title="Complete Task">
                <IconButton 
                  size="small" 
                  color="success"
                  onClick={handleComplete}
                  disabled={!task.assignee || task.status === 'COMPLETED'}
                >
                  <CheckCircleIcon />
                </IconButton>
              </Tooltip>
            </Stack>
          </Box>
        </CardContent>
      </Card>

      <TaskDialog
        open={assignDialogOpen}
        onClose={() => setAssignDialogOpen(false)}
        workflowId={workflowId}
        mode="assign"
        taskId={task.id}
      />
    </>
  );
};

export default TaskItem;
