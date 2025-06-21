package com.poly.dto.request;

import java.util.Set;
import java.util.UUID;

import com.poly.validator.ValidateRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import com.poly.validator.ValidatePassword;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserRequest {
    private UUID id;

    @Schema(example = "Cong Toan")
    @Size(min = 6, message = "NAME_INVALID")
    @Pattern(regexp = "^\\p{L}+( \\p{L}+)*$", message = "NAME_INVALID")
    private String name;

    @Schema(example = "letoan@gmail.com")
    @Email(message = "EMAIL_INVALID")
    private String email;

    @Schema(example = "0987654321")
    @Pattern(regexp = "^(\\+84|0)[0-9]{9}$", message = "PHONE_INVALID")
    private String phone;

    @Schema(example = "letoan")
    @Size(min = 6, message = "USERNAME_SHORT")
    @Pattern(regexp = "^[a-z0-9]+$", message = "USERNAME_INVALID")
    private String username;

    @Schema(example = "12345678")
    @ValidatePassword(message = "PASSWORD_SHORT")
    @Pattern(regexp = "^[A-Za-z0-9!@#$%^&*(),.?\":{}|<>]*$", message = "PASSWORD_INVALID")
    private String password;

    private String image;

    private String address;

    @Schema(example = "[\"ADMIN\", \"USER\"]")
    @ValidateRole(message = "ROLE_REQUIRED")
    private Set<String> roles;
}