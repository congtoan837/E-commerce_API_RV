package com.poly.controller;

import java.util.UUID;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.poly.dto.request.UserRequest;
import com.poly.dto.response.ApiResponse;
import com.poly.dto.response.PageResponse;
import com.poly.dto.response.user.UserResponse;
import com.poly.services.UserService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UsersController {
    UserService userService;

    @GetMapping("/get")
    public ApiResponse<?> getUser(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "20") int size,
            @RequestParam(required = false, defaultValue = "") String keyword) {
        return ApiResponse.<PageResponse<UserResponse>>builder()
                .result(userService.get(keyword, page, size))
                .build();
    }

    @PostMapping("/create")
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.create(request))
                .build();
    }

    @PutMapping("/update")
    public ApiResponse<UserResponse> updateUser(@RequestBody @Valid UserRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.update(request))
                .build();
    }

    @DeleteMapping("/delete/{userId}")
    public ApiResponse<Boolean> deleteUser(@PathVariable UUID userId) {
        userService.delete(userId);
        return ApiResponse.<Boolean>builder()
                .result(Boolean.TRUE)
                .build();
    }

    @GetMapping("/info")
    public ApiResponse<UserResponse> getInfo() {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getInfo())
                .build();
    }
}
