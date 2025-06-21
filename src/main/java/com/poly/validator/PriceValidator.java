package com.poly.validator;

import com.poly.dto.request.ProductRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.collections4.CollectionUtils;

public class PriceValidator implements ConstraintValidator<ValidatePrice, ProductRequest> {

    @Override
    public void initialize(ValidatePrice constraintAnnotation) {}

    @Override
    public boolean isValid(ProductRequest productRequest, ConstraintValidatorContext context) {
        // Nếu có variants, kiểm tra tất cả variants có price > 0
        if (CollectionUtils.isNotEmpty(productRequest.getVariants()))
            return productRequest.getVariants().stream()
                    .allMatch(variant -> variant.getPrice() > 0);
        // Nếu không có variants, kiểm tra price > 0
        return productRequest.getPrice() > 0;
    }
}
