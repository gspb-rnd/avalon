import React from 'react';
import { Box, Typography } from '@mui/material';
import { LoanApplicationList as LoansList } from '../../features/loans';

const LoanApplicationListPage: React.FC = () => {
  return (
    <Box p={3}>
      <LoansList />
    </Box>
  );
};

export default LoanApplicationListPage;
