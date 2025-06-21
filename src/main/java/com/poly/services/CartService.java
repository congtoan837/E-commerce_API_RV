package com.poly.services;

import java.util.*;

import org.springframework.stereotype.Service;

import com.poly.dto.request.CartRequest;
import com.poly.dto.response.cart.CartResponse;
import com.poly.entity.*;
import com.poly.exception.AppException;
import com.poly.exception.ErrorCode;
import com.poly.mapper.CartMapper;
import com.poly.repositories.CartRepository;
import com.poly.repositories.ProductRepository;
import com.poly.repositories.VariantRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartService {
    CartRepository cartRepository;
    ProductRepository productRepository;
    VariantRepository variantRepository;

    UserService userService;

    CartMapper cartMapper;

    private void calculatorTotalPrice(Cart cart) {
        // Nếu giỏ hàng rỗng
        if (cart.getCartItems().isEmpty()) {
            cart.setTotalPrice(0L);
            return;
        }
        // Tính toán tổng tiền giỏ hàng
        long totalPrice = cart.getCartItems().stream()
                .mapToLong(item -> {
                    long price = item.getVariant() != null
                            ? item.getVariant().getPrice()
                            : item.getProduct().getPrice();
                    return price * item.getQuantity();
                })
                .sum();

        cart.setTotalPrice(totalPrice);
    }

    private Cart createEmptyCart(User user) {
        return Cart.builder()
                .user(user)
                .cartItems(new HashSet<>())
                .build();
    }

    private boolean existItem(CartItem item, CartRequest request) {
        boolean sameProduct = Objects.equals(item.getProduct().getId(), request.getProduct_id());

        boolean sameVariant = Objects.nonNull(item.getVariant()) && Objects.equals(item.getVariant().getId(), request.getVariant_id());

        return sameProduct && sameVariant;
    }

    private void checkStock(Product product, Variant variant, int quantity) {
        long stockQuantity = variant != null ? variant.getStockQuantity() : product.getStockQuantity();
        if (stockQuantity < quantity) {
            throw new AppException(ErrorCode.OUT_OF_STOCK);
        }
    }

    public CartResponse addProductToCart(CartRequest request) {
        User user = userService.getCurrentUser();

        Cart cart = cartRepository.findByUserId(user.getId())
                .orElse(this.createEmptyCart(user));

        int quantity = request.getQuantity();

        Optional<CartItem> existingItemOptional = cart.getCartItems().stream()
                .filter(item -> existItem(item, request))
                .findFirst();

        if (existingItemOptional.isPresent()) {
            // Nếu sản phẩm đã có trong giỏ hàng, tăng số lượng
            CartItem existingItem = existingItemOptional.get();
            int newQuantity = existingItem.getQuantity() + quantity;

            this.checkStock(existingItem.getProduct(), existingItem.getVariant(), newQuantity);

            existingItem.setQuantity(newQuantity);
        } else {
            // Nếu sản phẩm chưa có, thêm sản phẩm mới
            Product product = productRepository.findById(request.getProduct_id())
                    .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

            Variant variant = null;
            if (!product.getVariants().isEmpty()) {
                // Yêu cầu chọn variant nếu sản phẩm có variants
                variant = variantRepository.findByIdAndProductId(request.getVariant_id(), product.getId())
                        .orElseThrow(() -> new AppException(ErrorCode.VARIANT_INVALID));

                // Kiểm tra tồn kho của Variant
                if (variant.getStockQuantity() < quantity) {
                    throw new AppException(ErrorCode.OUT_OF_STOCK);
                }
            }

            this.checkStock(product, variant, quantity);

            CartItem newItem = CartItem.builder()
                    .product(product)
                    .variant(variant)
                    .quantity(quantity)
                    .build();
            cart.getCartItems().add(newItem);
        }

        this.calculatorTotalPrice(cart);

        return cartMapper.toCartResponse(cartRepository.save(cart));
    }

    public CartResponse getCart() {
        User user = userService.getCurrentUser();

        Cart cart = cartRepository.findByUserId(user.getId())
                .orElse(this.createEmptyCart(user));

        this.calculatorTotalPrice(cart);

        return cartMapper.toCartResponse(cart);
    }

    public CartResponse updateCart(CartRequest request) {
        User user = userService.getCurrentUser();

        Cart cart = cartRepository.findByUserId(user.getId()).
                orElseThrow(() -> new AppException(ErrorCode.CART_NOT_FOUND));

        CartItem cartItem = cart.getCartItems().stream()
                .filter(item -> existItem(item, request))
                .findFirst()
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND_IN_CART));

        // Cập nhật số lượng
        cartItem.setQuantity(request.getQuantity());

        this.calculatorTotalPrice(cart);

        // Trả về CartResponse sau khi xóa sản phẩm
        return cartMapper.toCartResponse(cartRepository.save(cart));
    }

    public CartResponse removeFromCart(UUID itemId) {
        // Lấy user đang đăng nhập
        User user = userService.getCurrentUser();

        // Lấy giỏ hàng của user
        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseThrow(() -> new AppException(ErrorCode.CART_NOT_FOUND));

        // Tìm item trong giỏ hàng
        CartItem cartItem = cart.getCartItems().stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND_IN_CART));

        // Xóa CartItem này khỏi giỏ hàng
        cart.getCartItems().remove(cartItem);

        this.calculatorTotalPrice(cart);

        // Trả về CartResponse sau khi xóa sản phẩm
        return cartMapper.toCartResponse(cartRepository.save(cart));
    }
}
