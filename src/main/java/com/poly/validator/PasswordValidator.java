package com.poly.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class PasswordValidator implements ConstraintValidator<ValidatePassword, String> {

    @Override
    public void initialize(ValidatePassword constraintAnnotation) {}

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        // Nếu password là chuỗi rỗng thì bỏ qua
        if (Objects.isNull(password) || password.isBlank()) {
            return true;
        }
        // Kiểm tra độ dài nếu password không rỗng
        return password.length() >= 6;
    }
}
