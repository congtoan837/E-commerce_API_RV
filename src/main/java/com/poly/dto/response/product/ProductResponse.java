package com.poly.dto.response.product;

import java.util.Set;
import java.util.UUID;

import com.poly.dto.response.product.feedback.FeedbackResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {
    private UUID id;
    @Schema(example = "product 01")
    private String name;
    @Schema(example = "...")
    private String description;
    @Schema(example = "10000", nullable = true)
    private Long price;
    @Schema(example = "1", nullable = true)
    private Long stockQuantity;
    @Schema(example = "0")
    private Long soldQuantity;
    private Set<CategoryResponse> categories;
    private Set<VariantResponse> variants;
    private String coverImage;
    private Set<String> images;
}
