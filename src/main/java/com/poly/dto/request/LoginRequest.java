package com.poly.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @Schema(example = "admin")
    @NotBlank(message = "USERNAME_INVALID")
    private String username;
    @Schema(example = "admin")
    @NotBlank(message = "PASSWORD_INVALID")
    private String password;
}
