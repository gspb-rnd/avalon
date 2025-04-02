import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { ThemeProvider, createTheme } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import { Provider } from 'react-redux';
import { store } from './store';

import { AppLayout } from './layouts/AppLayout';

import Dashboard from './pages/Dashboard';
import ClientList from './pages/clients/ClientList';
import ClientDetails from './pages/clients/ClientDetails';
import LoanApplicationList from './pages/loans/LoanApplicationList';
import LoanApplicationDetails from './pages/loans/LoanApplicationDetails';
import CollateralList from './pages/collaterals/CollateralList';
import CollateralDetails from './pages/collaterals/CollateralDetails';
import DocumentList from './pages/documents/DocumentList';
import DocumentDetails from './pages/documents/DocumentDetails';
import WorkflowList from './pages/workflows/WorkflowList';
import WorkflowDetails from './pages/workflows/WorkflowDetails';

const theme = createTheme({
  palette: {
    primary: {
      main: '#1976d2',
    },
    secondary: {
      main: '#dc004e',
    },
    background: {
      default: '#f5f5f5',
    },
  },
  typography: {
    fontFamily: '"Roboto", "Helvetica", "Arial", sans-serif',
    h1: {
      fontSize: '2.5rem',
      fontWeight: 500,
    },
    h2: {
      fontSize: '2rem',
      fontWeight: 500,
    },
    h3: {
      fontSize: '1.75rem',
      fontWeight: 500,
    },
    h4: {
      fontSize: '1.5rem',
      fontWeight: 500,
    },
    h5: {
      fontSize: '1.25rem',
      fontWeight: 500,
    },
    h6: {
      fontSize: '1rem',
      fontWeight: 500,
    },
  },
  components: {
    MuiButton: {
      styleOverrides: {
        root: {
          textTransform: 'none',
        },
      },
    },
  },
});

const App: React.FC = () => {
  return (
    <Provider store={store}>
      <ThemeProvider theme={theme}>
        <CssBaseline />
        <Router>
          <AppLayout>
            <Routes>
              <Route path="/" element={<Dashboard />} />
              <Route path="/clients" element={<ClientList />} />
              <Route path="/clients/:id" element={<ClientDetails />} />
              <Route path="/loan-applications" element={<LoanApplicationList />} />
              <Route path="/loan-applications/:id" element={<LoanApplicationDetails />} />
              <Route path="/collaterals" element={<CollateralList />} />
              <Route path="/collaterals/:id" element={<CollateralDetails />} />
              <Route path="/documents" element={<DocumentList />} />
              <Route path="/documents/:id" element={<DocumentDetails />} />
              <Route path="/workflows" element={<WorkflowList />} />
              <Route path="/workflows/:id" element={<WorkflowDetails />} />
            </Routes>
          </AppLayout>
        </Router>
      </ThemeProvider>
    </Provider>
  );
};

export default App;
