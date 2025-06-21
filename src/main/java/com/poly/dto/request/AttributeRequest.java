package com.poly.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AttributeRequest {
    private UUID id;

    @NotEmpty
    private String name;
}
