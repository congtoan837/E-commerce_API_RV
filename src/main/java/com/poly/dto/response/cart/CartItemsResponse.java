package com.poly.dto.response.cart;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartItemsResponse {
    private UUID id;
    private ItemProductResponse product;
    private ItemVariantResponse variant;
    private int quantity;
}
