package com.poly.dto.response.order;

import java.util.UUID;

import com.poly.dto.response.cart.ItemProductResponse;
import com.poly.dto.response.cart.ItemVariantResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemResponse {
    private UUID id;
    private ItemProductResponse product;
    private ItemVariantResponse variant;
    private Long amount;
    private Long discount;
    private int quantity;
}
