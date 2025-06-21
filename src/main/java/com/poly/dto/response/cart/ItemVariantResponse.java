package com.poly.dto.response.cart;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.UUID;

@Getter
@Setter
public class ItemVariantResponse {
    private UUID id;
    private Map<String, String> attribute;
    private Long price;
}
