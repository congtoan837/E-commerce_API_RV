package com.poly.dto.response.product;

import java.util.Set;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VariantResponse {
    private UUID id;
    private Set<AttributeValueResponse> attributeValues;
    @Schema(example = "10000")
    private Long price;
    @Schema(example = "1")
    private int stockQuantity;
    @Schema(example = "0")
    private int soldQuantity;
}
