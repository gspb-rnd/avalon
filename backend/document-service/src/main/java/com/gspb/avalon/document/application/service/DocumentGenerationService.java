package com.gspb.avalon.document.application.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.UUID;

/**
 * Service for generating documents.
 */
@Service
@RequiredArgsConstructor
public class DocumentGenerationService {
    
    private final TemplateEngine templateEngine;
    private final StorageService storageService;
    
    private static final String DOCUMENTS_DIRECTORY = "documents";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    /**
     * Generates a loan agreement document.
     *
     * @param loanApplicationId The loan application ID
     * @param clientName The client name
     * @param loanAmount The loan amount
     * @param interestRate The interest rate
     * @param termInMonths The term in months
     * @param startDate The start date
     * @param maturityDate The maturity date
     * @param collateralDescription The collateral description
     * @return The URL of the generated document
     */
    public String generateLoanAgreement(UUID loanApplicationId, String clientName, String loanAmount,
                                       String interestRate, int termInMonths, LocalDateTime startDate,
                                       LocalDateTime maturityDate, String collateralDescription) {
        
        Context context = new Context();
        context.setVariable("loanApplicationId", loanApplicationId);
        context.setVariable("clientName", clientName);
        context.setVariable("loanAmount", loanAmount);
        context.setVariable("interestRate", interestRate);
        context.setVariable("termInMonths", termInMonths);
        context.setVariable("startDate", startDate.format(DATE_FORMATTER));
        context.setVariable("maturityDate", maturityDate.format(DATE_FORMATTER));
        context.setVariable("collateralDescription", collateralDescription);
        context.setVariable("generatedDate", LocalDateTime.now().format(DATE_FORMATTER));
        
        String htmlContent = templateEngine.process("loan-agreement", context);
        
        return generatePdfFromHtml(htmlContent, "loan-agreement-" + loanApplicationId);
    }
    
    /**
     * Generates a collateral agreement document.
     *
     * @param loanApplicationId The loan application ID
     * @param clientName The client name
     * @param collateralType The collateral type
     * @param collateralDescription The collateral description
     * @param collateralValue The collateral value
     * @param loanToValueRatio The loan-to-value ratio
     * @return The URL of the generated document
     */
    public String generateCollateralAgreement(UUID loanApplicationId, String clientName, String collateralType,
                                             String collateralDescription, String collateralValue,
                                             String loanToValueRatio) {
        
        Context context = new Context();
        context.setVariable("loanApplicationId", loanApplicationId);
        context.setVariable("clientName", clientName);
        context.setVariable("collateralType", collateralType);
        context.setVariable("collateralDescription", collateralDescription);
        context.setVariable("collateralValue", collateralValue);
        context.setVariable("loanToValueRatio", loanToValueRatio);
        context.setVariable("generatedDate", LocalDateTime.now().format(DATE_FORMATTER));
        
        String htmlContent = templateEngine.process("collateral-agreement", context);
        
        return generatePdfFromHtml(htmlContent, "collateral-agreement-" + loanApplicationId);
    }
    
    /**
     * Generates a term sheet document.
     *
     * @param loanApplicationId The loan application ID
     * @param clientName The client name
     * @param loanType The loan type
     * @param loanAmount The loan amount
     * @param interestRate The interest rate
     * @param interestRateType The interest rate type
     * @param termInMonths The term in months
     * @param paymentFrequency The payment frequency
     * @param startDate The start date
     * @param maturityDate The maturity date
     * @param originationFee The origination fee
     * @param earlyRepaymentAllowed Whether early repayment is allowed
     * @param earlyRepaymentFee The early repayment fee
     * @return The URL of the generated document
     */
    public String generateTermSheet(UUID loanApplicationId, String clientName, String loanType, String loanAmount,
                                   String interestRate, String interestRateType, int termInMonths,
                                   String paymentFrequency, LocalDateTime startDate, LocalDateTime maturityDate,
                                   String originationFee, boolean earlyRepaymentAllowed, String earlyRepaymentFee) {
        
        Context context = new Context();
        context.setVariable("loanApplicationId", loanApplicationId);
        context.setVariable("clientName", clientName);
        context.setVariable("loanType", loanType);
        context.setVariable("loanAmount", loanAmount);
        context.setVariable("interestRate", interestRate);
        context.setVariable("interestRateType", interestRateType);
        context.setVariable("termInMonths", termInMonths);
        context.setVariable("paymentFrequency", paymentFrequency);
        context.setVariable("startDate", startDate.format(DATE_FORMATTER));
        context.setVariable("maturityDate", maturityDate.format(DATE_FORMATTER));
        context.setVariable("originationFee", originationFee);
        context.setVariable("earlyRepaymentAllowed", earlyRepaymentAllowed);
        context.setVariable("earlyRepaymentFee", earlyRepaymentFee);
        context.setVariable("generatedDate", LocalDateTime.now().format(DATE_FORMATTER));
        
        String htmlContent = templateEngine.process("term-sheet", context);
        
        return generatePdfFromHtml(htmlContent, "term-sheet-" + loanApplicationId);
    }
    
    /**
     * Generates a custom document from a template.
     *
     * @param templateName The template name
     * @param variables The template variables
     * @param fileName The file name
     * @return The URL of the generated document
     */
    public String generateCustomDocument(String templateName, Map<String, Object> variables, String fileName) {
        Context context = new Context();
        variables.forEach(context::setVariable);
        context.setVariable("generatedDate", LocalDateTime.now().format(DATE_FORMATTER));
        
        String htmlContent = templateEngine.process(templateName, context);
        
        return generatePdfFromHtml(htmlContent, fileName);
    }
    
    /**
     * Generates a PDF from HTML content.
     *
     * @param htmlContent The HTML content
     * @param fileName The file name
     * @return The URL of the generated PDF
     */
    private String generatePdfFromHtml(String htmlContent, String fileName) {
        try {
            String tempFileName = fileName + "-" + UUID.randomUUID() + ".pdf";
            Path tempFilePath = Files.createTempFile(tempFileName, null);
            
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(tempFilePath.toFile()));
            document.open();
            
            document.add(new Paragraph(htmlContent));
            
            document.close();
            writer.close();
            
            String contentUrl = storageService.uploadFile(tempFilePath.toFile(), DOCUMENTS_DIRECTORY, tempFileName);
            
            Files.delete(tempFilePath);
            
            return contentUrl;
        } catch (DocumentException | IOException e) {
            throw new RuntimeException("Error generating PDF from HTML", e);
        }
    }
    
    /**
     * Adds a digital signature to a PDF.
     *
     * @param pdfUrl The URL of the PDF to sign
     * @param signatureUrl The URL of the signature image
     * @param signedBy The user who signed the document
     * @return The URL of the signed PDF
     */
    public String addDigitalSignature(String pdfUrl, String signatureUrl, String signedBy) {
        try {
            byte[] pdfBytes = storageService.downloadFile(pdfUrl);
            
            byte[] signatureBytes = storageService.downloadFile(signatureUrl);
            
            String tempFileName = "signed-" + UUID.randomUUID() + ".pdf";
            Path tempFilePath = Files.createTempFile(tempFileName, null);
            
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            document.open();
            
            document.add(new Paragraph("Original PDF: " + pdfUrl));
            
            document.add(new Paragraph("Signed by: " + signedBy));
            document.add(new Paragraph("Signature URL: " + signatureUrl));
            
            document.close();
            writer.close();
            
            Files.write(tempFilePath, outputStream.toByteArray());
            
            String signedPdfUrl = storageService.uploadFile(tempFilePath.toFile(), DOCUMENTS_DIRECTORY, tempFileName);
            
            Files.delete(tempFilePath);
            
            return signedPdfUrl;
        } catch (IOException | DocumentException e) {
            throw new RuntimeException("Error adding digital signature to PDF", e);
        }
    }
}
