package com.gspb.avalon.document.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Data Transfer Object for DocumentPackage.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentPackageDTO {
    
    private UUID id;
    private UUID loanApplicationId;
    private String name;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<UUID> documentIds = new ArrayList<>();
    private int documentCount;
}
