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
  Tabs,
  Tab,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  Card,
  CardContent,
  CardHeader,
  List,
  ListItem,
  ListItemText,
  IconButton,
  Tooltip
} from '@mui/material';
import {
  Edit as EditIcon,
  Add as AddIcon,
  Delete as DeleteIcon,
  CheckCircle as CheckCircleIcon,
  Cancel as CancelIcon,
  Send as SendIcon,
  Warning as WarningIcon
} from '@mui/icons-material';
import { format } from 'date-fns';
import { Chip } from '@mui/material';
import {
  fetchLoanApplication,
  submitLoanApplication,
  overrideValidationRule,
  selectLoanApplication,
  selectLoansLoading,
  selectLoansError,
  clearError,
  LoanApplication,
  ValidationRule
} from '../loansSlice';
import CollateralForm from './CollateralForm';

interface TabPanelProps {
  children?: React.ReactNode;
  index: number;
  value: number;
}

function TabPanel(props: TabPanelProps) {
  const { children, value, index, ...other } = props;

  return (
    <div
      role="tabpanel"
      hidden={value !== index}
      id={`loan-tabpanel-${index}`}
      aria-labelledby={`loan-tab-${index}`}
      {...other}
    >
      {value === index && (
        <Box sx={{ p: 3 }}>
          {children}
        </Box>
      )}
    </div>
  );
}

const LoanApplicationDetail: React.FC = () => {
  const { id } = useParams<{ id: string }>();
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const loanApplication = useSelector(selectLoanApplication);
  const loading = useSelector(selectLoansLoading);
  const error = useSelector(selectLoansError);

  const [tabValue, setTabValue] = useState(0);
  const [errorOpen, setErrorOpen] = useState(false);
  const [submitDialogOpen, setSubmitDialogOpen] = useState(false);
  const [overrideDialogOpen, setOverrideDialogOpen] = useState(false);
  const [selectedRule, setSelectedRule] = useState<ValidationRule | null>(null);
  const [overrideReason, setOverrideReason] = useState('');
  const [collateralDialogOpen, setCollateralDialogOpen] = useState(false);

  useEffect(() => {
    if (id) {
      dispatch(fetchLoanApplication(id));
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

  const handleTabChange = (event: React.SyntheticEvent, newValue: number) => {
    setTabValue(newValue);
  };

  const handleEditLoan = () => {
    navigate(`/loans/${id}/edit`);
  };

  const handleSubmitLoan = () => {
    setSubmitDialogOpen(false);
    if (id) {
      dispatch(submitLoanApplication(id));
    }
  };

  const handleOpenOverrideDialog = (rule: ValidationRule) => {
    setSelectedRule(rule);
    setOverrideReason('');
    setOverrideDialogOpen(true);
  };

  const handleOverrideRule = () => {
    if (id && selectedRule && overrideReason) {
      dispatch(overrideValidationRule({
        loanApplicationId: id,
        ruleId: selectedRule.id,
        overrideReason
      }));
      setOverrideDialogOpen(false);
    }
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

  const getSeverityColor = (severity: string) => {
    switch (severity) {
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

  if (loading && !loanApplication) {
    return (
      <Box display="flex" justifyContent="center" my={4}>
        <CircularProgress />
      </Box>
    );
  }

  if (!loanApplication) {
    return (
      <Box my={4}>
        <Alert severity="error">Loan application not found</Alert>
      </Box>
    );
  }

  return (
    <Box>
      <Box display="flex" justifyContent="space-between" alignItems="center" mb={3}>
        <Typography variant="h5" component="h1">
          Loan Application: {loanApplication.id}
        </Typography>
        <Box>
          {loanApplication.status === 'DRAFT' && (
            <>
              <Button
                variant="outlined"
                startIcon={<EditIcon />}
                onClick={handleEditLoan}
                sx={{ mr: 2 }}
              >
                Edit
              </Button>
              <Button
                variant="contained"
                color="primary"
                startIcon={<SendIcon />}
                onClick={() => setSubmitDialogOpen(true)}
              >
                Submit
              </Button>
            </>
          )}
        </Box>
      </Box>

      <Paper sx={{ mb: 3 }}>
        <Box p={3}>
          <Grid container spacing={3}>
            <Grid item xs={12} md={6}>
              <Typography variant="subtitle1">Client</Typography>
              <Typography variant="body1">{loanApplication.clientName}</Typography>
            </Grid>
            <Grid item xs={12} md={6}>
              <Typography variant="subtitle1">Status</Typography>
              <Chip
                label={loanApplication.status}
                color={getStatusColor(loanApplication.status) as any}
              />
            </Grid>
            <Grid item xs={12} md={6}>
              <Typography variant="subtitle1">Type</Typography>
              <Typography variant="body1">{loanApplication.type}</Typography>
            </Grid>
            <Grid item xs={12} md={6}>
              <Typography variant="subtitle1">Purpose</Typography>
              <Typography variant="body1">{loanApplication.purpose}</Typography>
            </Grid>
            <Grid item xs={12} md={6}>
              <Typography variant="subtitle1">Created By</Typography>
              <Typography variant="body1">{loanApplication.createdBy}</Typography>
            </Grid>
            <Grid item xs={12} md={6}>
              <Typography variant="subtitle1">Created At</Typography>
              <Typography variant="body1">
                {format(new Date(loanApplication.createdAt), 'MMM d, yyyy HH:mm')}
              </Typography>
            </Grid>
            {loanApplication.submittedAt && (
              <Grid item xs={12} md={6}>
                <Typography variant="subtitle1">Submitted At</Typography>
                <Typography variant="body1">
                  {format(new Date(loanApplication.submittedAt), 'MMM d, yyyy HH:mm')}
                </Typography>
              </Grid>
            )}
            {loanApplication.approvedAt && (
              <Grid item xs={12} md={6}>
                <Typography variant="subtitle1">Approved At</Typography>
                <Typography variant="body1">
                  {format(new Date(loanApplication.approvedAt), 'MMM d, yyyy HH:mm')}
                </Typography>
              </Grid>
            )}
            {loanApplication.rejectedAt && (
              <>
                <Grid item xs={12} md={6}>
                  <Typography variant="subtitle1">Rejected At</Typography>
                  <Typography variant="body1">
                    {format(new Date(loanApplication.rejectedAt), 'MMM d, yyyy HH:mm')}
                  </Typography>
                </Grid>
                <Grid item xs={12}>
                  <Typography variant="subtitle1">Rejection Reason</Typography>
                  <Typography variant="body1">{loanApplication.rejectionReason}</Typography>
                </Grid>
              </>
            )}
            {loanApplication.notes && (
              <Grid item xs={12}>
                <Typography variant="subtitle1">Notes</Typography>
                <Typography variant="body1">{loanApplication.notes}</Typography>
              </Grid>
            )}
          </Grid>
        </Box>
      </Paper>

      <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
        <Tabs value={tabValue} onChange={handleTabChange} aria-label="loan application tabs">
          <Tab label="Loan Terms" />
          <Tab label="Collateral" />
          <Tab label="Validation" />
        </Tabs>
      </Box>

      <TabPanel value={tabValue} index={0}>
        <Card>
          <CardHeader title="Loan Terms" />
          <CardContent>
            <Grid container spacing={3}>
              <Grid item xs={12} md={6}>
                <Typography variant="subtitle1">Amount</Typography>
                <Typography variant="body1">
                  {new Intl.NumberFormat('en-US', {
                    style: 'currency',
                    currency: loanApplication.terms.currency,
                  }).format(loanApplication.terms.amount)}
                </Typography>
              </Grid>
              <Grid item xs={12} md={6}>
                <Typography variant="subtitle1">Interest Rate</Typography>
                <Typography variant="body1">
                  {loanApplication.terms.interestRate}% ({loanApplication.terms.interestRateType})
                </Typography>
              </Grid>
              <Grid item xs={12} md={6}>
                <Typography variant="subtitle1">Term</Typography>
                <Typography variant="body1">
                  {loanApplication.terms.term} {loanApplication.terms.termUnit}
                </Typography>
              </Grid>
              <Grid item xs={12} md={6}>
                <Typography variant="subtitle1">Payment Frequency</Typography>
                <Typography variant="body1">{loanApplication.terms.paymentFrequency}</Typography>
              </Grid>
              {loanApplication.terms.startDate && (
                <Grid item xs={12} md={6}>
                  <Typography variant="subtitle1">Start Date</Typography>
                  <Typography variant="body1">
                    {format(new Date(loanApplication.terms.startDate), 'MMM d, yyyy')}
                  </Typography>
                </Grid>
              )}
              {loanApplication.terms.endDate && (
                <Grid item xs={12} md={6}>
                  <Typography variant="subtitle1">End Date</Typography>
                  <Typography variant="body1">
                    {format(new Date(loanApplication.terms.endDate), 'MMM d, yyyy')}
                  </Typography>
                </Grid>
              )}
            </Grid>
          </CardContent>
        </Card>
      </TabPanel>

      <TabPanel value={tabValue} index={1}>
        <Box display="flex" justifyContent="space-between" alignItems="center" mb={3}>
          <Typography variant="h6">Collateral</Typography>
          {loanApplication.status === 'DRAFT' && (
            <Button
              variant="contained"
              startIcon={<AddIcon />}
              onClick={() => setCollateralDialogOpen(true)}
            >
              Add Collateral
            </Button>
          )}
        </Box>

        {loanApplication.collaterals.length === 0 ? (
          <Paper sx={{ p: 3, textAlign: 'center' }}>
            <Typography>No collateral added yet</Typography>
          </Paper>
        ) : (
          <Grid container spacing={3}>
            {loanApplication.collaterals.map((collateral) => (
              <Grid item xs={12} md={6} key={collateral.id}>
                <Card>
                  <CardHeader
                    title={collateral.type}
                    subheader={collateral.description}
                    action={
                      loanApplication.status === 'DRAFT' && (
                        <IconButton aria-label="delete">
                          <DeleteIcon />
                        </IconButton>
                      )
                    }
                  />
                  <CardContent>
                    <Grid container spacing={2}>
                      <Grid item xs={6}>
                        <Typography variant="subtitle2">Estimated Value</Typography>
                        <Typography variant="body2">
                          {new Intl.NumberFormat('en-US', {
                            style: 'currency',
                            currency: loanApplication.terms.currency,
                          }).format(collateral.estimatedValue)}
                        </Typography>
                      </Grid>
                      <Grid item xs={6}>
                        <Typography variant="subtitle2">Status</Typography>
                        <Chip
                          label={collateral.status}
                          color={getStatusColor(collateral.status) as any}
                          size="small"
                        />
                      </Grid>
                      {collateral.appraisedValue && (
                        <Grid item xs={6}>
                          <Typography variant="subtitle2">Appraised Value</Typography>
                          <Typography variant="body2">
                            {new Intl.NumberFormat('en-US', {
                              style: 'currency',
                              currency: loanApplication.terms.currency,
                            }).format(collateral.appraisedValue)}
                          </Typography>
                        </Grid>
                      )}
                      {collateral.appraisalDate && (
                        <Grid item xs={6}>
                          <Typography variant="subtitle2">Appraisal Date</Typography>
                          <Typography variant="body2">
                            {format(new Date(collateral.appraisalDate), 'MMM d, yyyy')}
                          </Typography>
                        </Grid>
                      )}
                    </Grid>
                  </CardContent>
                </Card>
              </Grid>
            ))}
          </Grid>
        )}
      </TabPanel>

      <TabPanel value={tabValue} index={2}>
        <Typography variant="h6" gutterBottom>
          Validation Rules
        </Typography>

        {loanApplication.validationRules.length === 0 ? (
          <Paper sx={{ p: 3, textAlign: 'center' }}>
            <Typography>No validation rules available</Typography>
          </Paper>
        ) : (
          <List>
            {loanApplication.validationRules.map((rule) => (
              <ListItem
                key={rule.id}
                secondaryAction={
                  !rule.result && !rule.overridden && loanApplication.status === 'UNDER_REVIEW' && (
                    <Button
                      variant="outlined"
                      size="small"
                      onClick={() => handleOpenOverrideDialog(rule)}
                    >
                      Override
                    </Button>
                  )
                }
              >
                <ListItemText
                  primary={
                    <Box display="flex" alignItems="center">
                      {rule.result ? (
                        <CheckCircleIcon color="success" sx={{ mr: 1 }} />
                      ) : rule.overridden ? (
                        <WarningIcon color="warning" sx={{ mr: 1 }} />
                      ) : (
                        <CancelIcon color="error" sx={{ mr: 1 }} />
                      )}
                      <Typography variant="subtitle1">
                        {rule.name}
                        <Chip
                          label={rule.severity}
                          color={getSeverityColor(rule.severity) as any}
                          size="small"
                          sx={{ ml: 1 }}
                        />
                      </Typography>
                    </Box>
                  }
                  secondary={
                    <>
                      <Typography variant="body2">{rule.description}</Typography>
                      {!rule.result && (
                        <Typography variant="body2" color="error">
                          {rule.message}
                        </Typography>
                      )}
                      {rule.overridden && (
                        <Box mt={1}>
                          <Typography variant="body2">
                            <strong>Overridden by:</strong> {rule.overriddenBy}
                          </Typography>
                          <Typography variant="body2">
                            <strong>Reason:</strong> {rule.overrideReason}
                          </Typography>
                          <Typography variant="body2">
                            <strong>Date:</strong>{' '}
                            {format(new Date(rule.overrideDate!), 'MMM d, yyyy HH:mm')}
                          </Typography>
                        </Box>
                      )}
                    </>
                  }
                />
              </ListItem>
            ))}
          </List>
        )}
      </TabPanel>

      {/* Submit Dialog */}
      <Dialog
        open={submitDialogOpen}
        onClose={() => setSubmitDialogOpen(false)}
      >
        <DialogTitle>Submit Loan Application</DialogTitle>
        <DialogContent>
          <DialogContentText>
            Are you sure you want to submit this loan application? Once submitted, it cannot be edited.
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={() => setSubmitDialogOpen(false)}>Cancel</Button>
          <Button onClick={handleSubmitLoan} variant="contained" color="primary">
            Submit
          </Button>
        </DialogActions>
      </Dialog>

      {/* Override Dialog */}
      <Dialog
        open={overrideDialogOpen}
        onClose={() => setOverrideDialogOpen(false)}
      >
        <DialogTitle>Override Validation Rule</DialogTitle>
        <DialogContent>
          <DialogContentText>
            You are about to override the validation rule: {selectedRule?.name}
          </DialogContentText>
          <Box mt={2}>
            <Typography variant="subtitle2">Reason for Override:</Typography>
            <TextField
              autoFocus
              margin="dense"
              fullWidth
              multiline
              rows={4}
              value={overrideReason}
              onChange={(e) => setOverrideReason(e.target.value)}
              placeholder="Provide a detailed reason for overriding this validation rule..."
            />
          </Box>
        </DialogContent>
        <DialogActions>
          <Button onClick={() => setOverrideDialogOpen(false)}>Cancel</Button>
          <Button
            onClick={handleOverrideRule}
            variant="contained"
            color="primary"
            disabled={!overrideReason}
          >
            Override
          </Button>
        </DialogActions>
      </Dialog>

      {/* Collateral Dialog */}
      {collateralDialogOpen && (
        <CollateralForm
          open={collateralDialogOpen}
          onClose={() => setCollateralDialogOpen(false)}
          loanApplicationId={loanApplication.id}
          currency={loanApplication.terms.currency}
        />
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

export default LoanApplicationDetail;
