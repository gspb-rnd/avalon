import React from 'react';
import { Card, CardContent, CardHeader, Typography, Box } from '@mui/material';

interface DataCardProps {
  title: string;
  value: string | number;
  icon?: React.ReactNode;
  color?: string;
}

export const DataCard: React.FC<DataCardProps> = ({ title, value, icon, color = '#1976d2' }) => {
  return (
    <Card sx={{ height: '100%' }}>
      <CardContent>
        <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
          <Box>
            <Typography variant="subtitle2" color="text.secondary" gutterBottom>
              {title}
            </Typography>
            <Typography variant="h4" component="div" sx={{ fontWeight: 'bold' }}>
              {value}
            </Typography>
          </Box>
          {icon && (
            <Box
              sx={{
                backgroundColor: `${color}20`,
                borderRadius: '50%',
                p: 1,
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'center',
              }}
            >
              {React.cloneElement(icon as React.ReactElement, { sx: { color } })}
            </Box>
          )}
        </Box>
      </CardContent>
    </Card>
  );
};
