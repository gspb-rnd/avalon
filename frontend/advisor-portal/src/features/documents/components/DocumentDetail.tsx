import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useParams, useNavigate } from 'react-router-dom';
import {
  Box,
  Typography,
  Paper,
  Grid,
  Button,
  Divider,
  CircularProgress,
  Alert,
  Snackbar,
  IconButton,
  List,
  ListItem,
  ListItemText,
  ListItemSecondaryAction,
  Chip,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions
} from '@mui/material';
import {
  Download as DownloadIcon,
  Send as SendIcon,
  Add as AddIcon,
  Refresh as RefreshIcon
} from '@mui/icons-material';
import { format } from 'date-fns';
import {
  fetchDocumentById,
  downloadDocument,
  sendForSignature,
  selectCurrentDocument,
  selectDocumentsLoading,
  selectDocumentsError,
  clearError
} from '../documentsSlice';
import SignatureRequestForm from './SignatureRequestForm';

const DocumentDetail: React.FC = () => {
  const { id } = useParams<{ id: string }>();
  const dispatch = useDispatch();
  const navigate = useNavigate();
  
  const document = useSelector(selectCurrentDocument);
  const loading = useSelector(selectDocumentsLoading);
  const error = useSelector(selectDocumentsError);
  
  const [errorOpen, setErrorOpen] = useState(false);
  const [signatureFormOpen, setSignatureFormOpen] = useState(false);
  const [confirmSendOpen, setConfirmSendOpen] = useState(false);
  
  useEffect(() => {
    if (id) {
      dispatch(fetchDocumentById(id));
    }
  }, [dispatch, id]);
  
  useEffect(() => {
    if (error) {
      setErrorOpen(true);
    }
  }, [error]);
  
  const handleCloseError = () => {
    setErrorOpen(false);
    dispatch(clearError());
  };
  
  const handleRefresh = () => {
    if (id) {
      dispatch(fetchDocumentById(id));
    }
  };
  
  const handleDownload = () => {
    if (id) {
      dispatch(downloadDocument(id));
    }
  };
  
  const handleSendForSignature = () => {
    setConfirmSendOpen(false);
    if (id) {
      dispatch(sendForSignature(id));
    }
  };
  
  const handleBack = () => {
    navigate('/documents');
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
  
  const getSignatureStatusColor = (status: string) => {
    switch (status) {
      case 'PENDING':
        return 'warning';
      case 'SENT':
        return 'info';
      case 'COMPLETED':
        return 'success';
      case 'REJECTED':
        return 'error';
      case 'EXPIRED':
        return 'error';
      default:
        return 'default';
    }
  };
  
  if (loading && !document) {
    return (
      <Box display="flex" justifyContent="center" my={4}>
        <CircularProgress />
      </Box>
    );
  }
  
  if (!document) {
    return (
      <Box>
        <Alert severity="error">Document not found</Alert>
        <Button variant="contained" onClick={handleBack} sx={{ mt: 2 }}>
          Back to Documents
        </Button>
      </Box>
    );
  }
  
  return (
    <Box>
      <Box display="flex" justifyContent="space-between" alignItems="center" mb={3}>
        <Typography variant="h5" component="h1">Document Details</Typography>
        <Box>
          <IconButton onClick={handleRefresh} disabled={loading} sx={{ mr: 1 }}>
            <RefreshIcon />
          </IconButton>
          <Button 
            variant="outlined" 
            onClick={handleBack}
            sx={{ mr: 1 }}
          >
            Back
          </Button>
        </Box>
      </Box>
      
      <Paper sx={{ p: 3, mb: 3 }}>
        <Grid container spacing={3}>
          <Grid item xs={12} md={8}>
            <Typography variant="h6" gutterBottom>
              {document.name}
            </Typography>
            <Typography variant="body2" color="text.secondary" gutterBottom>
              ID: {document.id}
            </Typography>
          </Grid>
          <Grid item xs={12} md={4} display="flex" justifyContent="flex-end" alignItems="center">
            <Chip 
              label={document.status} 
              color={getStatusColor(document.status) as any}
              sx={{ mr: 1 }}
            />
            <Button
              variant="contained"
              startIcon={<DownloadIcon />}
              onClick={handleDownload}
              disabled={document.status === 'DRAFT' || loading}
              sx={{ mr: 1 }}
            >
              Download
            </Button>
            {document.status === 'GENERATED' && (
              <Button
                variant="contained"
                color="secondary"
                startIcon={<SendIcon />}
                onClick={() => setConfirmSendOpen(true)}
                disabled={loading}
              >
                Send for Signature
              </Button>
            )}
          </Grid>
        </Grid>
        
        <Divider sx={{ my: 2 }} />
        
        <Grid container spacing={3}>
          <Grid item xs={12} md={6}>
            <Typography variant="subtitle1" gutterBottom>
              Document Information
            </Typography>
            <Box component="dl" sx={{ display: 'grid', gridTemplateColumns: 'auto 1fr', gap: 2 }}>
              <Typography component="dt" fontWeight="bold">Type:</Typography>
              <Typography component="dd">{document.type}</Typography>
              
              <Typography component="dt" fontWeight="bold">Client:</Typography>
              <Typography component="dd">{document.clientName}</Typography>
              
              <Typography component="dt" fontWeight="bold">Loan Application:</Typography>
              <Typography component="dd">{document.loanApplicationId}</Typography>
              
              <Typography component="dt" fontWeight="bold">Created:</Typography>
              <Typography component="dd">
                {format(new Date(document.createdAt), 'MMM d, yyyy HH:mm')}
              </Typography>
              
              <Typography component="dt" fontWeight="bold">Last Updated:</Typography>
              <Typography component="dd">
                {format(new Date(document.updatedAt), 'MMM d, yyyy HH:mm')}
              </Typography>
            </Box>
          </Grid>
          
          <Grid item xs={12} md={6}>
            {document.fileUrl && (
              <Box>
                <Typography variant="subtitle1" gutterBottom>
                  Document Preview
                </Typography>
                <Box 
                  component="iframe" 
                  src={document.fileUrl} 
                  width="100%" 
                  height="300px"
                  sx={{ border: '1px solid #ddd' }}
                />
              </Box>
            )}
          </Grid>
        </Grid>
      </Paper>
      
      <Paper sx={{ p: 3 }}>
        <Box display="flex" justifyContent="space-between" alignItems="center" mb={2}>
          <Typography variant="h6">Signature Requests</Typography>
          {document.status === 'GENERATED' || document.status === 'PENDING_SIGNATURE' ? (
            <Button
              variant="contained"
              startIcon={<AddIcon />}
              onClick={() => setSignatureFormOpen(true)}
              disabled={loading}
            >
              Add Signature Request
            </Button>
          ) : null}
        </Box>
        
        {document.signatureRequests.length === 0 ? (
          <Typography variant="body2" color="text.secondary">
            No signature requests found
          </Typography>
        ) : (
          <List>
            {document.signatureRequests.map((request) => (
              <ListItem key={request.id} divider>
                <ListItemText
                  primary={`${request.recipientName} (${request.recipientEmail})`}
                  secondary={
                    <>
                      <Typography component="span" variant="body2">
                        Sent: {format(new Date(request.sentAt), 'MMM d, yyyy')}
                        {' • '}
                        Expires: {format(new Date(request.expiresAt), 'MMM d, yyyy')}
                        {request.completedAt && 
                          ` • Completed: ${format(new Date(request.completedAt), 'MMM d, yyyy')}`
                        }
                      </Typography>
                    </>
                  }
                />
                <ListItemSecondaryAction>
                  <Chip 
                    label={request.status} 
                    color={getSignatureStatusColor(request.status) as any}
                    size="small"
                  />
                </ListItemSecondaryAction>
              </ListItem>
            ))}
          </List>
        )}
      </Paper>
      
      <SignatureRequestForm
        open={signatureFormOpen}
        onClose={() => setSignatureFormOpen(false)}
        documentId={id || ''}
      />
      
      <Dialog
        open={confirmSendOpen}
        onClose={() => setConfirmSendOpen(false)}
      >
        <DialogTitle>Send Document for Signature</DialogTitle>
        <DialogContent>
          <Typography>
            Are you sure you want to send this document for signature? 
            {document.signatureRequests.length === 0 && 
              ' No signature requests have been added yet.'}
          </Typography>
        </DialogContent>
        <DialogActions>
          <Button onClick={() => setConfirmSendOpen(false)}>Cancel</Button>
          <Button 
            onClick={handleSendForSignature} 
            variant="contained" 
            color="primary"
            disabled={document.signatureRequests.length === 0}
          >
            Send
          </Button>
        </DialogActions>
      </Dialog>
      
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

export default DocumentDetail;
