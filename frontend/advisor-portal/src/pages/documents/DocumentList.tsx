import React from 'react';
import { Box } from '@mui/material';
import { DocumentList as DocumentListComponent } from '../../features/documents';

const DocumentListPage: React.FC = () => {
  return (
    <Box p={3}>
      <DocumentListComponent />
    </Box>
  );
};

export default DocumentListPage;
