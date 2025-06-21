package com.poly.dto.response.user;

import java.util.Set;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponse {
    @Schema(example = "54e672...")
    private UUID id;
    @Schema(example = "Cong Toan")
    private String name;
    @Schema(example = "letoan")
    private String username;
    @Schema(example = "letoan@gmail.com")
    private String email;
    @Schema(example = "0987...")
    private String phone;
    private String address;
    private String image;
    private Set<RoleResponse> roles;
}
