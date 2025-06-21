package com.poly.dto.response.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AttributeResponse {
    private UUID id;
    @Schema(example = "Màu sắc")
    private String name;
}
