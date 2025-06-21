package com.poly.dto.response.product.feedback;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbackUserResponse {
    private UUID id;
    private String username;
    private String name;
    private String image;
}
