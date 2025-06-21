package com.poly.ex;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.poly.exception.AppException;
import com.poly.exception.ErrorCode;
import lombok.AccessLevel;
import org.springframework.beans.factory.annotation.Value;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GoogleDriveService {
    JsonFactory jsonFactory = GsonFactory.getDefaultInstance();

    @Value("${google.drive.credential.path}")
    String account_key_path;

    public String uploadFile(MultipartFile uploadedFile) {
        try {
            File tempFile = File.createTempFile("temp", null);
            uploadedFile.transferTo(tempFile);

            String folderId = "1QI8kaXOX_wVtiwr7lVNwF2qCckWpq1cT";
            Drive drive = createDriveService();

            com.google.api.services.drive.model.File googleFile = new com.google.api.services.drive.model.File();
            googleFile.setName(tempFile.getName());
            googleFile.setParents(Collections.singletonList(folderId));
            String mimeType = "application/octet-stream";
            FileContent fileContent = new FileContent(mimeType, tempFile);
            // Tạo file trên Google Drive
            com.google.api.services.drive.model.File file = drive.files().create(googleFile, fileContent)
                    .setFields("id")
                    .execute();
            // Set quyền public cho file vừa upload
            com.google.api.services.drive.model.Permission permission = new com.google.api.services.drive.model.Permission()
                    .setType("anyone")
                    .setRole("reader");
            drive.permissions().create(file.getId(), permission).execute();
            String imageUrl = ""
//                    "https://drive.google.com/uc?export=view&id="
                    + file.getId();
            tempFile.delete();
            return imageUrl;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new AppException(ErrorCode.SYSTEM_INTERNAL_ERROR);
        }
    }

    private Drive createDriveService() throws GeneralSecurityException, IOException {
        GoogleCredentials credentials = GoogleCredentials
                .fromStream(new FileInputStream(account_key_path))
                .createScoped(Collections.singleton(DriveScopes.DRIVE));

        return new Drive.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                jsonFactory,
                new HttpCredentialsAdapter(credentials))
                .setApplicationName("MySpringApp")
                .build();
    }
}