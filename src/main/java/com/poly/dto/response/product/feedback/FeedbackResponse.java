package com.poly.dto.response.product.feedback;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbackResponse {
    private UUID id;
    private int rating;
    private String title;
    private String comment;
    private FeedbackUserResponse user;
}
