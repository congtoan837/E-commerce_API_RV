package com.poly.dto.request;

import java.util.UUID;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbackRequest {
    private UUID id;

    @NotNull
    @Min(1)
    @Max(5)
    private int star;

    @NotNull
    @Size(min = 10, max = 100, message = "")
    private String title;

    @NotNull
    @Size(min = 10, max = 500)
    private String comment;

    @NotNull
    private UUID productId;

    @NotNull
    private UUID userId;
}
