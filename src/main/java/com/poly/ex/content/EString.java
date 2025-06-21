package com.poly.ex.content;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EString {
    PENDING("pending"),
    PROCESSING("processing"),
    CANCELLED("cancelled");

    private final String name;
}
