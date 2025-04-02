import React from 'react';
import { Typography, Box, Breadcrumbs, Link } from '@mui/material';
import { NavigateNext as NavigateNextIcon } from '@mui/icons-material';

interface BreadcrumbItem {
  label: string;
  href?: string;
}

interface PageHeaderProps {
  title: string;
  breadcrumbs?: BreadcrumbItem[];
}

export const PageHeader: React.FC<PageHeaderProps> = ({ title, breadcrumbs }) => {
  return (
    <Box sx={{ mb: 4 }}>
      {breadcrumbs && breadcrumbs.length > 0 && (
        <Breadcrumbs
          separator={<NavigateNextIcon fontSize="small" />}
          aria-label="breadcrumb"
          sx={{ mb: 1 }}
        >
          {breadcrumbs.map((breadcrumb, index) => {
            const isLast = index === breadcrumbs.length - 1;
            return isLast ? (
              <Typography key={index} color="text.primary">
                {breadcrumb.label}
              </Typography>
            ) : (
              <Link key={index} color="inherit" href={breadcrumb.href}>
                {breadcrumb.label}
              </Link>
            );
          })}
        </Breadcrumbs>
      )}
      <Typography variant="h4" component="h1" gutterBottom>
        {title}
      </Typography>
    </Box>
  );
};
