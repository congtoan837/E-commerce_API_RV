package com.poly.mapper;

import com.poly.dto.response.PageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.poly.dto.request.FeedbackRequest;
import com.poly.dto.response.product.feedback.FeedbackResponse;
import com.poly.entity.Feedback;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface FeedbackMapper {
    Feedback toFeedback(FeedbackRequest request);

    FeedbackResponse toFeedbackResponse(Feedback feedback);

    void updateFeedbackFromFeedbackRequest(@MappingTarget Feedback feedback, FeedbackRequest request);

    default PageResponse<FeedbackResponse> toPageResponse(Page<Feedback> productPage) {
        return PageResponse.<FeedbackResponse>builder()
                .totalPages(productPage.getTotalPages())
                .pageSize(productPage.getSize())
                .totalElements(productPage.getTotalElements())
                .data(productPage.map(this::toFeedbackResponse).getContent()) // Tự động map từng phần tử
                .build();
    }
}
