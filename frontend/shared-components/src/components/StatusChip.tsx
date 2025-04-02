import React from 'react';
import { Chip, ChipProps } from '@mui/material';

interface StatusConfig {
  label: string;
  color: ChipProps['color'];
}

interface StatusMapping {
  [key: string]: StatusConfig;
}

const clientStatusMapping: StatusMapping = {
  PENDING_KYC: { label: 'Pending KYC', color: 'warning' },
  KYC_IN_PROGRESS: { label: 'KYC In Progress', color: 'info' },
  KYC_COMPLETED: { label: 'KYC Completed', color: 'success' },
  KYC_FAILED: { label: 'KYC Failed', color: 'error' },
  ACTIVE: { label: 'Active', color: 'success' },
  INACTIVE: { label: 'Inactive', color: 'default' },
  BLOCKED: { label: 'Blocked', color: 'error' },
};

const loanStatusMapping: StatusMapping = {
  DRAFT: { label: 'Draft', color: 'default' },
  SUBMITTED: { label: 'Submitted', color: 'info' },
  UNDER_REVIEW: { label: 'Under Review', color: 'warning' },
  APPROVED: { label: 'Approved', color: 'success' },
  REJECTED: { label: 'Rejected', color: 'error' },
  PENDING_DOCUMENTS: { label: 'Pending Documents', color: 'warning' },
  PENDING_SIGNATURE: { label: 'Pending Signature', color: 'warning' },
  ACTIVE: { label: 'Active', color: 'success' },
  CLOSED: { label: 'Closed', color: 'default' },
  DEFAULTED: { label: 'Defaulted', color: 'error' },
};

const documentStatusMapping: StatusMapping = {
  DRAFT: { label: 'Draft', color: 'default' },
  GENERATED: { label: 'Generated', color: 'info' },
  SENT: { label: 'Sent', color: 'warning' },
  SIGNED: { label: 'Signed', color: 'success' },
  REJECTED: { label: 'Rejected', color: 'error' },
  EXPIRED: { label: 'Expired', color: 'error' },
};

const workflowStatusMapping: StatusMapping = {
  PENDING: { label: 'Pending', color: 'warning' },
  IN_PROGRESS: { label: 'In Progress', color: 'info' },
  COMPLETED: { label: 'Completed', color: 'success' },
  FAILED: { label: 'Failed', color: 'error' },
  CANCELLED: { label: 'Cancelled', color: 'default' },
};

interface StatusChipProps {
  status: string;
  type: 'client' | 'loan' | 'document' | 'workflow';
}

export const StatusChip: React.FC<StatusChipProps> = ({ status, type }) => {
  let mapping: StatusMapping;

  switch (type) {
    case 'client':
      mapping = clientStatusMapping;
      break;
    case 'loan':
      mapping = loanStatusMapping;
      break;
    case 'document':
      mapping = documentStatusMapping;
      break;
    case 'workflow':
      mapping = workflowStatusMapping;
      break;
    default:
      mapping = {};
  }

  const statusConfig = mapping[status] || { label: status, color: 'default' };

  return <Chip label={statusConfig.label} color={statusConfig.color} size="small" />;
};
