import React, { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import {
  Box,
  Typography,
  Paper,
  Grid,
  TextField,
  Button,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
  CircularProgress,
  Alert,
  Snackbar,
  Divider
} from '@mui/material';
import {
  generateDocuments,
  selectDocumentsLoading,
  selectDocumentsError,
  clearError
} from '../documentsSlice';

const DocumentForm: React.FC = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  
  const loading = useSelector(selectDocumentsLoading);
  const error = useSelector(selectDocumentsError);
  
  const [loanApplicationId, setLoanApplicationId] = useState('');
  const [errorOpen, setErrorOpen] = useState(false);
  
  useEffect(() => {
    if (error) {
      setErrorOpen(true);
    }
  }, [error]);
  
  const handleCloseError = () => {
    setErrorOpen(false);
    dispatch(clearError());
  };
  
  const handleSubmit = async () => {
    if (!loanApplicationId) {
      return;
    }
    
    try {
      await dispatch(generateDocuments(loanApplicationId));
      navigate('/documents');
    } catch (error) {
      console.error('Failed to generate documents:', error);
    }
  };
  
  const handleCancel = () => {
    navigate('/documents');
  };
  
  return (
    <Box>
      <Box display="flex" justifyContent="space-between" alignItems="center" mb={3}>
        <Typography variant="h5" component="h1">
          Generate Documents
        </Typography>
      </Box>
      
      <Paper sx={{ p: 3, mb: 3 }}>
        <Typography variant="body1" paragraph>
          Generate loan documents for a loan application. This will create all required documents based on the loan application details.
        </Typography>
        
        <Divider sx={{ my: 2 }} />
        
        <Grid container spacing={3}>
          <Grid item xs={12}>
            <TextField
              label="Loan Application ID"
              value={loanApplicationId}
              onChange={(e) => setLoanApplicationId(e.target.value)}
              fullWidth
              required
              placeholder="Enter the loan application ID"
              helperText="Enter the ID of the loan application for which to generate documents"
            />
          </Grid>
        </Grid>
      </Paper>
      
      <Box display="flex" justifyContent="flex-end" mt={3}>
        <Button
          variant="outlined"
          onClick={handleCancel}
          sx={{ mr: 2 }}
          disabled={loading}
        >
          Cancel
        </Button>
        <Button
          variant="contained"
          color="primary"
          onClick={handleSubmit}
          disabled={loading || !loanApplicationId}
        >
          {loading ? (
            <CircularProgress size={24} />
          ) : (
            'Generate Documents'
          )}
        </Button>
      </Box>
      
      <Snackbar
        open={errorOpen}
        autoHideDuration={6000}
        onClose={handleCloseError}
        anchorOrigin={{ vertical: 'bottom', horizontal: 'center' }}
      >
        <Alert onClose={handleCloseError} severity="error">
          {error}
        </Alert>
      </Snackbar>
    </Box>
  );
};

export default DocumentForm;
