package com.poly.controller;

import com.poly.dto.response.ApiResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import com.poly.ex.GoogleDriveService;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BucketController {
    GoogleDriveService googleDriveService;

    @PostMapping("/uploadFile")
    public ApiResponse uploadFileGoogleDrive(@RequestParam("image") MultipartFile file) {
        return ApiResponse
                .builder()
                .result(googleDriveService.uploadFile(file))
                .build();
    }
}