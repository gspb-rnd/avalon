import React from 'react';
import { Typography, Box } from '@mui/material';

interface HeaderProps {
  title: string;
}

export const Header: React.FC<HeaderProps> = ({ title }) => {
  return (
    <Box>
      <Typography variant="h6" component="div">
        {title}
      </Typography>
    </Box>
  );
};
