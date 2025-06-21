package com.poly.dto.response.user;

import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleResponse {
    @Schema(example = "USER")
    private String name;
    @Schema(example = "[\"READ\", \"WRITE\"]")
    private Set<String> permissions;
}
