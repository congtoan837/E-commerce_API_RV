package com.poly.dto.request;

import java.util.Set;
import java.util.UUID;

import com.poly.validator.ValidatePrice;
import com.poly.validator.ValidateRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ValidatePrice(message = "PRICE_POSITIVE")
public class ProductRequest {
    private UUID id;
    @Schema(example = "product 01")
    @Pattern(regexp = "^(?!\\s*$)[A-Za-z ]+$", message = "NAME_INVALID")
    private String name;
    @Schema(example = "...")
    private String description;
    @Schema(example = "10000", nullable = true)
    @Positive(message = "PRICE_POSITIVE")
    private long price;
    @Schema(example = "1", nullable = true)
    @Positive(message = "STOCK_POSITIVE")
    private long stockQuantity;
    @Schema(example = "[\"AO\", \"QUAN\"]")
    @NotEmpty(message = "CATEGORY_NOT_FOUND")
    private Set<String> categories;

    private String coverImage;

    private Set<String> images;

    private Set<VariantRequest> variants;
}
