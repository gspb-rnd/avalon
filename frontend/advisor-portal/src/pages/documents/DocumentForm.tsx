import React from 'react';
import { Box } from '@mui/material';
import { DocumentForm as DocumentFormComponent } from '../../features/documents';

const DocumentFormPage: React.FC = () => {
  return (
    <Box p={3}>
      <DocumentFormComponent />
    </Box>
  );
};

export default DocumentFormPage;
