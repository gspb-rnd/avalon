package com.gspb.avalon.collateral.domain.model;

/**
 * Enum representing the status of a collateral valuation.
 */
public enum ValuationStatus {
    PENDING,
    IN_PROGRESS,
    COMPLETED,
    REJECTED,
    EXPIRED,
    REQUIRES_MANUAL_REVIEW
}
