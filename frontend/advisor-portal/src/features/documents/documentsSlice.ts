import { createSlice, createAsyncThunk, PayloadAction } from '@reduxjs/toolkit';
import axios from 'axios';

export interface Document {
  id: string;
  name: string;
  type: string;
  status: string;
  loanApplicationId: string;
  clientId: string;
  clientName: string;
  createdAt: string;
  updatedAt: string;
  fileUrl?: string;
  signatureRequests: SignatureRequest[];
}

export interface SignatureRequest {
  id: string;
  documentId: string;
  recipientEmail: string;
  recipientName: string;
  status: string;
  sentAt: string;
  completedAt?: string;
  expiresAt: string;
}

export interface DocumentPackage {
  id: string;
  name: string;
  loanApplicationId: string;
  clientId: string;
  clientName: string;
  status: string;
  createdAt: string;
  updatedAt: string;
  documents: Document[];
}

interface DocumentsState {
  documents: Document[];
  documentPackages: DocumentPackage[];
  currentDocument: Document | null;
  currentDocumentPackage: DocumentPackage | null;
  loading: boolean;
  error: string | null;
}

const initialState: DocumentsState = {
  documents: [],
  documentPackages: [],
  currentDocument: null,
  currentDocumentPackage: null,
  loading: false,
  error: null
};

const API_BASE_URL = 'http://localhost:8080/api/documents';

export const fetchDocuments = createAsyncThunk(
  'documents/fetchDocuments',
  async (_, { rejectWithValue }) => {
    try {
      const response = await axios.get(`${API_BASE_URL}`);
      return response.data;
    } catch (error: any) {
      return rejectWithValue(error.response?.data?.message || 'Failed to fetch documents');
    }
  }
);

export const fetchDocumentById = createAsyncThunk(
  'documents/fetchDocumentById',
  async (id: string, { rejectWithValue }) => {
    try {
      const response = await axios.get(`${API_BASE_URL}/${id}`);
      return response.data;
    } catch (error: any) {
      return rejectWithValue(error.response?.data?.message || 'Failed to fetch document');
    }
  }
);

export const fetchDocumentPackages = createAsyncThunk(
  'documents/fetchDocumentPackages',
  async (_, { rejectWithValue }) => {
    try {
      const response = await axios.get(`${API_BASE_URL}/packages`);
      return response.data;
    } catch (error: any) {
      return rejectWithValue(error.response?.data?.message || 'Failed to fetch document packages');
    }
  }
);

export const fetchDocumentPackageById = createAsyncThunk(
  'documents/fetchDocumentPackageById',
  async (id: string, { rejectWithValue }) => {
    try {
      const response = await axios.get(`${API_BASE_URL}/packages/${id}`);
      return response.data;
    } catch (error: any) {
      return rejectWithValue(error.response?.data?.message || 'Failed to fetch document package');
    }
  }
);

export const generateDocuments = createAsyncThunk(
  'documents/generateDocuments',
  async (loanApplicationId: string, { rejectWithValue }) => {
    try {
      const response = await axios.post(`${API_BASE_URL}/generate`, { loanApplicationId });
      return response.data;
    } catch (error: any) {
      return rejectWithValue(error.response?.data?.message || 'Failed to generate documents');
    }
  }
);

export const sendForSignature = createAsyncThunk(
  'documents/sendForSignature',
  async (documentId: string, { rejectWithValue }) => {
    try {
      const response = await axios.post(`${API_BASE_URL}/${documentId}/send-for-signature`);
      return response.data;
    } catch (error: any) {
      return rejectWithValue(error.response?.data?.message || 'Failed to send document for signature');
    }
  }
);

export const addSignatureRequest = createAsyncThunk(
  'documents/addSignatureRequest',
  async ({ documentId, signatureRequest }: { documentId: string, signatureRequest: Partial<SignatureRequest> }, { rejectWithValue }) => {
    try {
      const response = await axios.post(`${API_BASE_URL}/${documentId}/signature-requests`, signatureRequest);
      return response.data;
    } catch (error: any) {
      return rejectWithValue(error.response?.data?.message || 'Failed to add signature request');
    }
  }
);

export const downloadDocument = createAsyncThunk(
  'documents/downloadDocument',
  async (documentId: string, { rejectWithValue }) => {
    try {
      const response = await axios.get(`${API_BASE_URL}/${documentId}/download`, {
        responseType: 'blob'
      });
      
      const url = window.URL.createObjectURL(new Blob([response.data]));
      
      const link = document.createElement('a');
      link.href = url;
      link.setAttribute('download', `document-${documentId}.pdf`);
      document.body.appendChild(link);
      link.click();
      link.remove();
      
      return documentId;
    } catch (error: any) {
      return rejectWithValue(error.response?.data?.message || 'Failed to download document');
    }
  }
);

const documentsSlice = createSlice({
  name: 'documents',
  initialState,
  reducers: {
    clearCurrentDocument: (state) => {
      state.currentDocument = null;
    },
    clearCurrentDocumentPackage: (state) => {
      state.currentDocumentPackage = null;
    },
    clearError: (state) => {
      state.error = null;
    }
  },
  extraReducers: (builder) => {
    builder
      .addCase(fetchDocuments.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(fetchDocuments.fulfilled, (state, action: PayloadAction<Document[]>) => {
        state.loading = false;
        state.documents = action.payload;
      })
      .addCase(fetchDocuments.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload as string;
      })
      
      .addCase(fetchDocumentById.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(fetchDocumentById.fulfilled, (state, action: PayloadAction<Document>) => {
        state.loading = false;
        state.currentDocument = action.payload;
      })
      .addCase(fetchDocumentById.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload as string;
      })
      
      .addCase(fetchDocumentPackages.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(fetchDocumentPackages.fulfilled, (state, action: PayloadAction<DocumentPackage[]>) => {
        state.loading = false;
        state.documentPackages = action.payload;
      })
      .addCase(fetchDocumentPackages.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload as string;
      })
      
      .addCase(fetchDocumentPackageById.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(fetchDocumentPackageById.fulfilled, (state, action: PayloadAction<DocumentPackage>) => {
        state.loading = false;
        state.currentDocumentPackage = action.payload;
      })
      .addCase(fetchDocumentPackageById.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload as string;
      })
      
      .addCase(generateDocuments.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(generateDocuments.fulfilled, (state, action: PayloadAction<DocumentPackage>) => {
        state.loading = false;
        state.currentDocumentPackage = action.payload;
        state.documentPackages = state.documentPackages.map(pkg => 
          pkg.id === action.payload.id ? action.payload : pkg
        );
        if (!state.documentPackages.some(pkg => pkg.id === action.payload.id)) {
          state.documentPackages.push(action.payload);
        }
      })
      .addCase(generateDocuments.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload as string;
      })
      
      .addCase(sendForSignature.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(sendForSignature.fulfilled, (state, action: PayloadAction<Document>) => {
        state.loading = false;
        state.currentDocument = action.payload;
        state.documents = state.documents.map(doc => 
          doc.id === action.payload.id ? action.payload : doc
        );
        
        if (state.currentDocumentPackage) {
          state.currentDocumentPackage.documents = state.currentDocumentPackage.documents.map(doc => 
            doc.id === action.payload.id ? action.payload : doc
          );
        }
      })
      .addCase(sendForSignature.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload as string;
      })
      
      .addCase(addSignatureRequest.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(addSignatureRequest.fulfilled, (state, action: PayloadAction<Document>) => {
        state.loading = false;
        state.currentDocument = action.payload;
        state.documents = state.documents.map(doc => 
          doc.id === action.payload.id ? action.payload : doc
        );
        
        if (state.currentDocumentPackage) {
          state.currentDocumentPackage.documents = state.currentDocumentPackage.documents.map(doc => 
            doc.id === action.payload.id ? action.payload : doc
          );
        }
      })
      .addCase(addSignatureRequest.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload as string;
      })
      
      .addCase(downloadDocument.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(downloadDocument.fulfilled, (state) => {
        state.loading = false;
      })
      .addCase(downloadDocument.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload as string;
      });
  }
});

export const { clearCurrentDocument, clearCurrentDocumentPackage, clearError } = documentsSlice.actions;

export const selectDocuments = (state: { documents: DocumentsState }) => state.documents.documents;
export const selectDocumentPackages = (state: { documents: DocumentsState }) => state.documents.documentPackages;
export const selectCurrentDocument = (state: { documents: DocumentsState }) => state.documents.currentDocument;
export const selectCurrentDocumentPackage = (state: { documents: DocumentsState }) => state.documents.currentDocumentPackage;
export const selectDocumentsLoading = (state: { documents: DocumentsState }) => state.documents.loading;
export const selectDocumentsError = (state: { documents: DocumentsState }) => state.documents.error;

export default documentsSlice.reducer;
