package com.poly.dto.response.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponse {
    @Schema(example = "AO")
    private String name;
    @Schema(example = "...")
    private String description;
}
