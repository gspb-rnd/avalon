package com.gspb.avalon.document.application.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

/**
 * Service for storing and retrieving files.
 */
@Service
public class StorageService {
    
    private final Path rootLocation;
    private final String baseUrl;
    
    /**
     * Creates a new storage service.
     */
    public StorageService() {
        this.rootLocation = Paths.get("uploads");
        this.baseUrl = "http://localhost:8083/api/documents/files";
        
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage", e);
        }
    }
    
    /**
     * Uploads a file.
     *
     * @param file The file to upload
     * @param directory The directory to upload to
     * @param fileName The file name
     * @return The URL of the uploaded file
     */
    public String uploadFile(File file, String directory, String fileName) {
        try {
            Path targetDirectory = this.rootLocation.resolve(directory);
            Files.createDirectories(targetDirectory);
            
            Path targetLocation = targetDirectory.resolve(fileName);
            Files.copy(file.toPath(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            
            return baseUrl + "/" + directory + "/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file " + fileName, e);
        }
    }
    
    /**
     * Uploads a multipart file.
     *
     * @param file The file to upload
     * @param directory The directory to upload to
     * @return The URL of the uploaded file
     */
    public String uploadFile(MultipartFile file, String directory) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Failed to store empty file");
            }
            
            String originalFileName = file.getOriginalFilename();
            String fileExtension = "";
            if (originalFileName != null && originalFileName.contains(".")) {
                fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            }
            
            String fileName = UUID.randomUUID().toString() + fileExtension;
            
            Path targetDirectory = this.rootLocation.resolve(directory);
            Files.createDirectories(targetDirectory);
            
            Path targetLocation = targetDirectory.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            
            return baseUrl + "/" + directory + "/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }
    
    /**
     * Downloads a file.
     *
     * @param fileUrl The URL of the file to download
     * @return The file bytes
     */
    public byte[] downloadFile(String fileUrl) {
        try {
            String filePath = fileUrl.replace(baseUrl + "/", "");
            Path file = rootLocation.resolve(filePath);
            return Files.readAllBytes(file);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file " + fileUrl, e);
        }
    }
    
    /**
     * Gets a file.
     *
     * @param directory The directory
     * @param fileName The file name
     * @return The file
     */
    public File getFile(String directory, String fileName) {
        Path file = rootLocation.resolve(directory).resolve(fileName);
        return file.toFile();
    }
    
    /**
     * Deletes a file.
     *
     * @param fileUrl The URL of the file to delete
     */
    public void deleteFile(String fileUrl) {
        try {
            String filePath = fileUrl.replace(baseUrl + "/", "");
            Path file = rootLocation.resolve(filePath);
            Files.deleteIfExists(file);
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete file " + fileUrl, e);
        }
    }
}
