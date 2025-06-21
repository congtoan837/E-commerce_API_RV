package com.poly.exception;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
    SYSTEM_INTERNAL_ERROR(-9999, "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR),

    IMAGE_NOT_FOUND(-1000, "Image not found", HttpStatus.BAD_REQUEST),

    // Xác thực và bảo mật
    INVALID_CREDENTIALS(1001, "Invalid credentials", HttpStatus.UNAUTHORIZED),
    ACCESS_DENIED(1002, "Access denied", HttpStatus.FORBIDDEN),
    INVALID_TOKEN(1003, "Invalid token signature", HttpStatus.BAD_REQUEST),
    TOKEN_EXPIRED(1004, "Refresh token has expired", HttpStatus.BAD_REQUEST),

    // Người dùng
    USER_NOT_FOUND(2001, "User not found", HttpStatus.NOT_FOUND),
    USERNAME_EXISTS(2002, "Username exists", HttpStatus.CONFLICT),
    USERNAME_INVALID(2003, "Username invalid", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(2004, "Password invalid", HttpStatus.BAD_REQUEST),
    USERNAME_SHORT(2005, "Username must be at least 6 characters", HttpStatus.BAD_REQUEST),
    PASSWORD_SHORT(2006, "Password must be at least 6 characters", HttpStatus.BAD_REQUEST),
    ROLE_REQUIRED(2007, "Roles required", HttpStatus.BAD_REQUEST),  // thiếu role khi tạo
    PERMISSION_REQUIRED(2008, "Permission required", HttpStatus.BAD_REQUEST), // thiếu Permission khi tạo
    INPUT_VALID(2009, "Input invalid", HttpStatus.BAD_REQUEST),
    NAME_INVALID(2010, "Name invalid", HttpStatus.BAD_REQUEST),
    EMAIL_INVALID(2011, "Email invalid", HttpStatus.BAD_REQUEST),
    PHONE_INVALID(2012, "Phone invalid", HttpStatus.BAD_REQUEST),

    // Sản phẩm
    PRODUCT_NOT_FOUND(3001, "Product not found", HttpStatus.BAD_REQUEST),
    CATEGORY_NOT_FOUND(3002, "Category not found", HttpStatus.BAD_REQUEST),
    VARIANT_INVALID(3003, "Variant invalid", HttpStatus.BAD_REQUEST),
    VARIANT_VALUE_REQUIRED(3004, "Variant must be 1 attribute", HttpStatus.BAD_REQUEST),
    VARIANT_ATTRIBUTE_INVALID(3005, "Variant attribute id invalid", HttpStatus.BAD_REQUEST),
    FEEDBACK_NOT_FOUND(3006, "Feedback not found", HttpStatus.BAD_REQUEST),
    OUT_OF_STOCK(3009, "Out of stock", HttpStatus.BAD_REQUEST),
    PRICE_POSITIVE(3010, "Price must be positive", HttpStatus.BAD_REQUEST),

    // Giỏ hàng và đơn hàng
    CART_NOT_FOUND(4001, "Cart not found", HttpStatus.BAD_REQUEST),
    PRODUCT_NOT_FOUND_IN_CART(4002, "Product not found in cart", HttpStatus.BAD_REQUEST),
    VOUCHER_NOT_FOUND(4003, "Voucher not found", HttpStatus.BAD_REQUEST),
    VOUCHER_INVALID(4004, "Voucher invalid", HttpStatus.BAD_REQUEST),
    ORDER_NOT_FOUND(4005, "Order not found", HttpStatus.BAD_REQUEST),
    QUANTITY_INVALID(4006, "Quantity invalid", HttpStatus.BAD_REQUEST),

    ;
    int code; // Mã lỗi
    String message; // Thông báo lỗi
    HttpStatus statusCode; // HTTP Status cho phản hồi
}
