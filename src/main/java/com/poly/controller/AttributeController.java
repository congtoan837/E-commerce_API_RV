package com.poly.controller;

import com.poly.dto.request.AttributeRequest;
import com.poly.dto.response.ApiResponse;
import com.poly.dto.response.product.AttributeResponse;
import com.poly.services.AttributeService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attribute")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AttributeController {
    AttributeService attributeService;

    @PostMapping("/create")
    public ApiResponse<AttributeResponse> createCategory(@RequestBody @Valid AttributeRequest request) {
        return ApiResponse.<AttributeResponse>builder()
                .result(attributeService.create(request))
                .build();
    }

    @GetMapping("/get")
    public ApiResponse<List<AttributeResponse>> getCategory() {
        return ApiResponse.<List<AttributeResponse>>builder()
                .result(attributeService.getAll())
                .build();
    }
}
