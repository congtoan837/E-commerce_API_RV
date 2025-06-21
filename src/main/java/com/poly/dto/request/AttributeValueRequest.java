package com.poly.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AttributeValueRequest {
    @NotNull
    private UUID attributeId;

    @Schema(example = "Màu sắc")
    @NotEmpty
    private String value;
}
