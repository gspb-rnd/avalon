import React from 'react';
import { Typography, Box } from '@mui/material';

interface FooterProps {
  companyName: string;
  year: number;
}

export const Footer: React.FC<FooterProps> = ({ companyName, year }) => {
  return (
    <Box sx={{ mt: 4, pt: 2, borderTop: '1px solid #eaeaea', textAlign: 'center' }}>
      <Typography variant="body2" color="text.secondary">
        © {year} {companyName}. All rights reserved.
      </Typography>
    </Box>
  );
};
