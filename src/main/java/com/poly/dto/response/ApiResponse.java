package com.poly.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ApiResponse<T> {
    @Schema(example = "1")
    @Builder.Default
    private int code = 1;

    @Schema(example = "...", nullable = true)
    @Builder.Default
    private String message = null;
    private T result;
}
