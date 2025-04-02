import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
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
  TextField,
  InputAdornment,
  IconButton,
  CircularProgress,
  Alert,
  Snackbar,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle
} from '@mui/material';
import { 
  Add as AddIcon,
  Search as SearchIcon,
  Refresh as RefreshIcon
} from '@mui/icons-material';
import { format } from 'date-fns';
import { useNavigate } from 'react-router-dom';
import { 
  fetchLoanApplications, 
  selectLoanApplications, 
  selectLoansLoading, 
  selectLoansError,
  clearError
} from '../loansSlice';
import { Chip } from '@mui/material';

const LoanApplicationList: React.FC = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const loanApplications = useSelector(selectLoanApplications);
  const loading = useSelector(selectLoansLoading);
  const error = useSelector(selectLoansError);
  
  const [searchTerm, setSearchTerm] = useState('');
  const [errorOpen, setErrorOpen] = useState(false);
  
  useEffect(() => {
    dispatch(fetchLoanApplications());
  }, [dispatch]);
  
  useEffect(() => {
    if (error) {
      setErrorOpen(true);
    }
  }, [error]);
  
  const handleRefresh = () => {
    dispatch(fetchLoanApplications());
  };
  
  const handleCreateNew = () => {
    navigate('/loans/new');
  };
  
  const handleViewDetails = (id: string) => {
    navigate(`/loans/${id}`);
  };
  
  const handleCloseError = () => {
    setErrorOpen(false);
    dispatch(clearError());
  };
  
  const getStatusColor = (status: string) => {
    switch (status) {
      case 'DRAFT':
        return 'default';
      case 'SUBMITTED':
        return 'info';
      case 'UNDER_REVIEW':
        return 'warning';
      case 'APPROVED':
        return 'success';
      case 'REJECTED':
        return 'error';
      case 'PENDING_DOCUMENTS':
      case 'PENDING_SIGNATURE':
        return 'warning';
      case 'ACTIVE':
        return 'success';
      case 'CLOSED':
        return 'default';
      case 'DEFAULTED':
        return 'error';
      default:
        return 'default';
    }
  };
  
  const filteredLoanApplications = loanApplications.filter(loan => 
    loan.clientName.toLowerCase().includes(searchTerm.toLowerCase()) ||
    loan.id.toLowerCase().includes(searchTerm.toLowerCase()) ||
    loan.type.toLowerCase().includes(searchTerm.toLowerCase()) ||
    loan.status.toLowerCase().includes(searchTerm.toLowerCase())
  );
  
  return (
    <Box>
      <Box display="flex" justifyContent="space-between" alignItems="center" mb={3}>
        <Typography variant="h5" component="h1">Loan Applications</Typography>
        <Button 
          variant="contained" 
          color="primary" 
          startIcon={<AddIcon />}
          onClick={handleCreateNew}
        >
          Create New Application
        </Button>
      </Box>
      
      <Box display="flex" justifyContent="space-between" alignItems="center" mb={3}>
        <TextField
          placeholder="Search loan applications..."
          variant="outlined"
          size="small"
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          InputProps={{
            startAdornment: (
              <InputAdornment position="start">
                <SearchIcon />
              </InputAdornment>
            ),
          }}
          sx={{ width: 300 }}
        />
        <IconButton onClick={handleRefresh} disabled={loading}>
          <RefreshIcon />
        </IconButton>
      </Box>
      
      {loading ? (
        <Box display="flex" justifyContent="center" my={4}>
          <CircularProgress />
        </Box>
      ) : filteredLoanApplications.length === 0 ? (
        <Paper sx={{ p: 3, textAlign: 'center' }}>
          <Typography>No loan applications found</Typography>
        </Paper>
      ) : (
        <TableContainer component={Paper}>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell>ID</TableCell>
                <TableCell>Client</TableCell>
                <TableCell>Type</TableCell>
                <TableCell>Purpose</TableCell>
                <TableCell>Amount</TableCell>
                <TableCell>Status</TableCell>
                <TableCell>Created</TableCell>
                <TableCell>Actions</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {filteredLoanApplications.map((loan) => (
                <TableRow key={loan.id}>
                  <TableCell>{loan.id}</TableCell>
                  <TableCell>{loan.clientName}</TableCell>
                  <TableCell>{loan.type}</TableCell>
                  <TableCell>{loan.purpose}</TableCell>
                  <TableCell>
                    {new Intl.NumberFormat('en-US', {
                      style: 'currency',
                      currency: loan.terms.currency,
                    }).format(loan.terms.amount)}
                  </TableCell>
                  <TableCell>
                    <Chip 
                      label={loan.status} 
                      color={getStatusColor(loan.status) as any}
                      size="small"
                    />
                  </TableCell>
                  <TableCell>
                    {format(new Date(loan.createdAt), 'MMM d, yyyy')}
                  </TableCell>
                  <TableCell>
                    <Button 
                      variant="outlined" 
                      size="small"
                      onClick={() => handleViewDetails(loan.id)}
                    >
                      View Details
                    </Button>
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      )}
      
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

export default LoanApplicationList;
