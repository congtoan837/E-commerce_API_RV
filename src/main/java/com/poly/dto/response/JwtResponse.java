package com.poly.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class JwtResponse {
    @Schema(example = "eyJhbGciOiJIUzI1...")
    private String accessToken;
    @Schema(example = "eyJhbGciOiJIUzI1...")
    private String refreshToken;
}
