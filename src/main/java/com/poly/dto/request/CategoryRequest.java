package com.poly.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequest {
    @NotBlank(message = "NAME_INVALID")
    private String name;
    private String description;
}
