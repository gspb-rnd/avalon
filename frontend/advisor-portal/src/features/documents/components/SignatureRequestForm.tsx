import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import {
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  Button,
  TextField,
  Grid,
  CircularProgress,
  Typography,
  FormHelperText
} from '@mui/material';
import { addDays } from 'date-fns';
import { addSignatureRequest } from '../documentsSlice';

interface SignatureRequestFormProps {
  open: boolean;
  onClose: () => void;
  documentId: string;
}

const SignatureRequestForm: React.FC<SignatureRequestFormProps> = ({
  open,
  onClose,
  documentId
}) => {
  const dispatch = useDispatch();
  const [loading, setLoading] = useState(false);
  const [recipientName, setRecipientName] = useState('');
  const [recipientEmail, setRecipientEmail] = useState('');
  const [expirationDays, setExpirationDays] = useState('30');
  
  const [nameError, setNameError] = useState('');
  const [emailError, setEmailError] = useState('');
  const [expirationError, setExpirationError] = useState('');
  
  const validateForm = () => {
    let isValid = true;
    
    if (!recipientName.trim()) {
      setNameError('Recipient name is required');
      isValid = false;
    } else {
      setNameError('');
    }
    
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!recipientEmail.trim()) {
      setEmailError('Recipient email is required');
      isValid = false;
    } else if (!emailRegex.test(recipientEmail)) {
      setEmailError('Please enter a valid email address');
      isValid = false;
    } else {
      setEmailError('');
    }
    
    const days = Number(expirationDays);
    if (isNaN(days) || days < 1 || days > 90) {
      setExpirationError('Expiration must be between 1 and 90 days');
      isValid = false;
    } else {
      setExpirationError('');
    }
    
    return isValid;
  };
  
  const handleSubmit = async () => {
    if (!validateForm()) {
      return;
    }
    
    setLoading(true);
    
    try {
      const expiresAt = addDays(new Date(), Number(expirationDays)).toISOString();
      
      await dispatch(addSignatureRequest({
        documentId,
        signatureRequest: {
          recipientName,
          recipientEmail,
          expiresAt
        }
      }));
      
      handleClose();
    } catch (error) {
      console.error('Failed to add signature request:', error);
    } finally {
      setLoading(false);
    }
  };
  
  const handleClose = () => {
    setRecipientName('');
    setRecipientEmail('');
    setExpirationDays('30');
    setNameError('');
    setEmailError('');
    setExpirationError('');
    onClose();
  };
  
  return (
    <Dialog open={open} onClose={handleClose} maxWidth="sm" fullWidth>
      <DialogTitle>Add Signature Request</DialogTitle>
      <DialogContent>
        <Grid container spacing={3} sx={{ mt: 1 }}>
          <Grid item xs={12}>
            <Typography variant="body2" color="text.secondary" gutterBottom>
              Add a recipient who needs to sign this document. They will receive an email with instructions.
            </Typography>
          </Grid>
          
          <Grid item xs={12}>
            <TextField
              label="Recipient Name"
              value={recipientName}
              onChange={(e) => setRecipientName(e.target.value)}
              fullWidth
              required
              error={!!nameError}
              helperText={nameError}
            />
          </Grid>
          
          <Grid item xs={12}>
            <TextField
              label="Recipient Email"
              value={recipientEmail}
              onChange={(e) => setRecipientEmail(e.target.value)}
              fullWidth
              required
              error={!!emailError}
              helperText={emailError}
              type="email"
            />
          </Grid>
          
          <Grid item xs={12}>
            <TextField
              label="Expiration (Days)"
              value={expirationDays}
              onChange={(e) => {
                const value = e.target.value;
                if (value === '' || /^\d+$/.test(value)) {
                  setExpirationDays(value);
                }
              }}
              fullWidth
              required
              type="text"
              error={!!expirationError}
              helperText={expirationError}
            />
            <FormHelperText>
              The signature request will expire after this many days if not completed
            </FormHelperText>
          </Grid>
        </Grid>
      </DialogContent>
      <DialogActions>
        <Button onClick={handleClose} disabled={loading}>
          Cancel
        </Button>
        <Button 
          onClick={handleSubmit} 
          variant="contained" 
          color="primary"
          disabled={loading}
        >
          {loading ? <CircularProgress size={24} /> : 'Add Signature Request'}
        </Button>
      </DialogActions>
    </Dialog>
  );
};

export default SignatureRequestForm;
