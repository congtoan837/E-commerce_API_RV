package com.poly.dto.request;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleRequest {
    @NotBlank(message = "NAME_INVALID")
    private String name;
    private String description;
    @NotEmpty(message = "PERMISSION_INVALID")
    private Set<String> permissions;
}
