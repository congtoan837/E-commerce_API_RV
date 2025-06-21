package com.poly.dto.response.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AttributeValueResponse {
    private AttributeResponse attribute;
    @Schema(example = "Đỏ")
    private String value;
}
