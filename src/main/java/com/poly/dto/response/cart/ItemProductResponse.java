package com.poly.dto.response.cart;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ItemProductResponse {
    private UUID id;
    private String name;
    private Long price;
}
