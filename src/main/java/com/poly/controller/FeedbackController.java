package com.poly.controller;

import com.poly.dto.request.FeedbackRequest;
import com.poly.dto.response.ApiResponse;
import com.poly.dto.response.PageResponse;
import com.poly.dto.response.product.feedback.FeedbackResponse;
import com.poly.services.FeedbackService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/feedback")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FeedbackController {
    FeedbackService feedbackService;

    @PostMapping("/create")
    public ApiResponse<FeedbackResponse> createFeedback(@RequestBody @Valid FeedbackRequest request) {
        return ApiResponse.<FeedbackResponse>builder()
                .result(feedbackService.create(request))
                .build();
    }

    @GetMapping("/get")
    public ApiResponse<PageResponse<FeedbackResponse>> getProduct(
            @RequestParam UUID productId,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "20") int size) {
        return ApiResponse.<PageResponse<FeedbackResponse>>builder()
                .result(feedbackService.getByProduct(productId, page, size))
                .build();
    }
}
