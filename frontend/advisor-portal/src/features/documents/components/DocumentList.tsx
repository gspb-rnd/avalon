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
  Tabs,
  Tab
} from '@mui/material';
import { 
  Add as AddIcon,
  Search as SearchIcon,
  Refresh as RefreshIcon,
  Download as DownloadIcon,
  Send as SendIcon
} from '@mui/icons-material';
import { format } from 'date-fns';
import { useNavigate } from 'react-router-dom';
import { 
  fetchDocuments, 
  fetchDocumentPackages,
  downloadDocument,
  sendForSignature,
  selectDocuments, 
  selectDocumentPackages,
  selectDocumentsLoading, 
  selectDocumentsError,
  clearError
} from '../documentsSlice';
import { Chip } from '@mui/material';

interface TabPanelProps {
  children?: React.ReactNode;
  index: number;
  value: number;
}

const TabPanel = (props: TabPanelProps) => {
  const { children, value, index, ...other } = props;

  return (
    <div
      role="tabpanel"
      hidden={value !== index}
      id={`document-tabpanel-${index}`}
      aria-labelledby={`document-tab-${index}`}
      {...other}
    >
      {value === index && (
        <Box sx={{ pt: 3 }}>
          {children}
        </Box>
      )}
    </div>
  );
};

const DocumentList: React.FC = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const documents = useSelector(selectDocuments);
  const documentPackages = useSelector(selectDocumentPackages);
  const loading = useSelector(selectDocumentsLoading);
  const error = useSelector(selectDocumentsError);
  
  const [searchTerm, setSearchTerm] = useState('');
  const [errorOpen, setErrorOpen] = useState(false);
  const [tabValue, setTabValue] = useState(0);
  
  useEffect(() => {
    dispatch(fetchDocuments());
    dispatch(fetchDocumentPackages());
  }, [dispatch]);
  
  useEffect(() => {
    if (error) {
      setErrorOpen(true);
    }
  }, [error]);
  
  const handleRefresh = () => {
    dispatch(fetchDocuments());
    dispatch(fetchDocumentPackages());
  };
  
  const handleCreateNew = () => {
    navigate('/documents/new');
  };
  
  const handleViewDetails = (id: string) => {
    navigate(`/documents/${id}`);
  };
  
  const handleViewPackage = (id: string) => {
    navigate(`/documents/packages/${id}`);
  };
  
  const handleDownload = (id: string) => {
    dispatch(downloadDocument(id));
  };
  
  const handleSendForSignature = (id: string) => {
    dispatch(sendForSignature(id));
  };
  
  const handleCloseError = () => {
    setErrorOpen(false);
    dispatch(clearError());
  };
  
  const handleTabChange = (event: React.SyntheticEvent, newValue: number) => {
    setTabValue(newValue);
  };
  
  const getStatusColor = (status: string) => {
    switch (status) {
      case 'DRAFT':
        return 'default';
      case 'GENERATED':
        return 'info';
      case 'PENDING_SIGNATURE':
        return 'warning';
      case 'SIGNED':
        return 'success';
      case 'REJECTED':
        return 'error';
      case 'EXPIRED':
        return 'error';
      default:
        return 'default';
    }
  };
  
  const filteredDocuments = documents.filter(doc => 
    doc.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
    doc.id.toLowerCase().includes(searchTerm.toLowerCase()) ||
    doc.type.toLowerCase().includes(searchTerm.toLowerCase()) ||
    doc.status.toLowerCase().includes(searchTerm.toLowerCase()) ||
    doc.clientName.toLowerCase().includes(searchTerm.toLowerCase())
  );
  
  const filteredPackages = documentPackages.filter(pkg => 
    pkg.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
    pkg.id.toLowerCase().includes(searchTerm.toLowerCase()) ||
    pkg.status.toLowerCase().includes(searchTerm.toLowerCase()) ||
    pkg.clientName.toLowerCase().includes(searchTerm.toLowerCase())
  );
  
  return (
    <Box>
      <Box display="flex" justifyContent="space-between" alignItems="center" mb={3}>
        <Typography variant="h5" component="h1">Documents</Typography>
        <Button 
          variant="contained" 
          color="primary" 
          startIcon={<AddIcon />}
          onClick={handleCreateNew}
        >
          Generate Documents
        </Button>
      </Box>
      
      <Box display="flex" justifyContent="space-between" alignItems="center" mb={3}>
        <TextField
          placeholder="Search documents..."
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
      
      <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
        <Tabs value={tabValue} onChange={handleTabChange} aria-label="document tabs">
          <Tab label="Individual Documents" id="document-tab-0" />
          <Tab label="Document Packages" id="document-tab-1" />
        </Tabs>
      </Box>
      
      {loading ? (
        <Box display="flex" justifyContent="center" my={4}>
          <CircularProgress />
        </Box>
      ) : (
        <>
          <TabPanel value={tabValue} index={0}>
            {filteredDocuments.length === 0 ? (
              <Paper sx={{ p: 3, textAlign: 'center' }}>
                <Typography>No documents found</Typography>
              </Paper>
            ) : (
              <TableContainer component={Paper}>
                <Table>
                  <TableHead>
                    <TableRow>
                      <TableCell>ID</TableCell>
                      <TableCell>Name</TableCell>
                      <TableCell>Type</TableCell>
                      <TableCell>Client</TableCell>
                      <TableCell>Loan Application</TableCell>
                      <TableCell>Status</TableCell>
                      <TableCell>Created</TableCell>
                      <TableCell>Actions</TableCell>
                    </TableRow>
                  </TableHead>
                  <TableBody>
                    {filteredDocuments.map((doc) => (
                      <TableRow key={doc.id}>
                        <TableCell>{doc.id}</TableCell>
                        <TableCell>{doc.name}</TableCell>
                        <TableCell>{doc.type}</TableCell>
                        <TableCell>{doc.clientName}</TableCell>
                        <TableCell>{doc.loanApplicationId}</TableCell>
                        <TableCell>
                          <Chip 
                            label={doc.status} 
                            color={getStatusColor(doc.status) as any}
                            size="small"
                          />
                        </TableCell>
                        <TableCell>
                          {format(new Date(doc.createdAt), 'MMM d, yyyy')}
                        </TableCell>
                        <TableCell>
                          <Box display="flex" gap={1}>
                            <Button 
                              variant="outlined" 
                              size="small"
                              onClick={() => handleViewDetails(doc.id)}
                            >
                              View
                            </Button>
                            <IconButton 
                              size="small" 
                              color="primary"
                              onClick={() => handleDownload(doc.id)}
                              disabled={doc.status === 'DRAFT'}
                            >
                              <DownloadIcon fontSize="small" />
                            </IconButton>
                            <IconButton 
                              size="small" 
                              color="secondary"
                              onClick={() => handleSendForSignature(doc.id)}
                              disabled={doc.status !== 'GENERATED'}
                            >
                              <SendIcon fontSize="small" />
                            </IconButton>
                          </Box>
                        </TableCell>
                      </TableRow>
                    ))}
                  </TableBody>
                </Table>
              </TableContainer>
            )}
          </TabPanel>
          
          <TabPanel value={tabValue} index={1}>
            {filteredPackages.length === 0 ? (
              <Paper sx={{ p: 3, textAlign: 'center' }}>
                <Typography>No document packages found</Typography>
              </Paper>
            ) : (
              <TableContainer component={Paper}>
                <Table>
                  <TableHead>
                    <TableRow>
                      <TableCell>ID</TableCell>
                      <TableCell>Name</TableCell>
                      <TableCell>Client</TableCell>
                      <TableCell>Loan Application</TableCell>
                      <TableCell>Status</TableCell>
                      <TableCell>Documents</TableCell>
                      <TableCell>Created</TableCell>
                      <TableCell>Actions</TableCell>
                    </TableRow>
                  </TableHead>
                  <TableBody>
                    {filteredPackages.map((pkg) => (
                      <TableRow key={pkg.id}>
                        <TableCell>{pkg.id}</TableCell>
                        <TableCell>{pkg.name}</TableCell>
                        <TableCell>{pkg.clientName}</TableCell>
                        <TableCell>{pkg.loanApplicationId}</TableCell>
                        <TableCell>
                          <Chip 
                            label={pkg.status} 
                            color={getStatusColor(pkg.status) as any}
                            size="small"
                          />
                        </TableCell>
                        <TableCell>{pkg.documents.length}</TableCell>
                        <TableCell>
                          {format(new Date(pkg.createdAt), 'MMM d, yyyy')}
                        </TableCell>
                        <TableCell>
                          <Button 
                            variant="outlined" 
                            size="small"
                            onClick={() => handleViewPackage(pkg.id)}
                          >
                            View Package
                          </Button>
                        </TableCell>
                      </TableRow>
                    ))}
                  </TableBody>
                </Table>
              </TableContainer>
            )}
          </TabPanel>
        </>
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

export default DocumentList;
