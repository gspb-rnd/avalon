import React from 'react';
import { Box } from '@mui/material';
import { DocumentDetail as DocumentDetailComponent } from '../../features/documents';

const DocumentDetailPage: React.FC = () => {
  return (
    <Box p={3}>
      <DocumentDetailComponent />
    </Box>
  );
};

export default DocumentDetailPage;
