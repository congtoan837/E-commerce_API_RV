package com.poly.dto.request;

import java.util.Set;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VariantRequest {
    private UUID id;
    @NotEmpty
    private Set<AttributeValueRequest> attributeValues;
    @Schema(example = "10000")
    @Positive(message = "PRICE_POSITIVE")
    private long price;
    @Schema(example = "1")
    @Positive(message = "STOCK_POSITIVE")
    private long stockQuantity;
}
