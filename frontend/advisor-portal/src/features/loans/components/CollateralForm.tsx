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
  FormControl,
  InputLabel,
  Select,
  MenuItem,
  InputAdornment,
  Typography,
  CircularProgress
} from '@mui/material';
import { addCollateral } from '../loansSlice';

interface CollateralFormProps {
  open: boolean;
  onClose: () => void;
  loanApplicationId: string;
  currency: string;
}

const CollateralForm: React.FC<CollateralFormProps> = ({
  open,
  onClose,
  loanApplicationId,
  currency
}) => {
  const dispatch = useDispatch();
  const [loading, setLoading] = useState(false);
  const [type, setType] = useState('');
  const [description, setDescription] = useState('');
  const [estimatedValue, setEstimatedValue] = useState<number | ''>('');
  
  const handleSubmit = async () => {
    if (!type || !description || estimatedValue === '') {
      return;
    }
    
    setLoading(true);
    
    try {
      await dispatch(addCollateral({
        loanApplicationId,
        collateral: {
          type,
          description,
          estimatedValue: Number(estimatedValue),
          status: 'PENDING_VALUATION'
        }
      }));
      
      handleClose();
    } catch (error) {
      console.error('Failed to add collateral:', error);
    } finally {
      setLoading(false);
    }
  };
  
  const handleClose = () => {
    setType('');
    setDescription('');
    setEstimatedValue('');
    onClose();
  };
  
  return (
    <Dialog open={open} onClose={handleClose} maxWidth="md" fullWidth>
      <DialogTitle>Add Collateral</DialogTitle>
      <DialogContent>
        <Grid container spacing={3} sx={{ mt: 1 }}>
          <Grid item xs={12}>
            <FormControl fullWidth required>
              <InputLabel>Collateral Type</InputLabel>
              <Select
                value={type}
                onChange={(e) => setType(e.target.value)}
                label="Collateral Type"
              >
                <MenuItem value="REAL_ESTATE">Real Estate</MenuItem>
                <MenuItem value="SECURITIES">Securities/Investment Portfolio</MenuItem>
                <MenuItem value="CASH_DEPOSITS">Cash Deposits</MenuItem>
                <MenuItem value="INSURANCE_POLICY">Insurance Policy</MenuItem>
                <MenuItem value="VEHICLE">Vehicle</MenuItem>
                <MenuItem value="ART">Art/Collectibles</MenuItem>
                <MenuItem value="JEWELRY">Jewelry</MenuItem>
                <MenuItem value="OTHER">Other</MenuItem>
              </Select>
            </FormControl>
          </Grid>
          
          <Grid item xs={12}>
            <TextField
              label="Description"
              value={description}
              onChange={(e) => setDescription(e.target.value)}
              fullWidth
              required
              multiline
              rows={3}
              placeholder="Provide detailed description of the collateral..."
            />
          </Grid>
          
          <Grid item xs={12}>
            <TextField
              label="Estimated Value"
              value={estimatedValue}
              onChange={(e) => {
                const value = e.target.value;
                if (value === '' || /^\d+(\.\d{0,2})?$/.test(value)) {
                  setEstimatedValue(value === '' ? '' : Number(value));
                }
              }}
              fullWidth
              required
              type="text"
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start">{currency}</InputAdornment>
                ),
              }}
            />
            <Typography variant="caption" color="text.secondary">
              Provide your best estimate of the current market value
            </Typography>
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
          disabled={loading || !type || !description || estimatedValue === ''}
        >
          {loading ? <CircularProgress size={24} /> : 'Add Collateral'}
        </Button>
      </DialogActions>
    </Dialog>
  );
};

export default CollateralForm;
