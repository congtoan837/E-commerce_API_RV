package com.poly.validator;

import com.poly.ex.content.ERole;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Arrays;
import java.util.Set;

public class RoleValidator implements ConstraintValidator<ValidateRole, Set<String>> {
    @Override
    public void initialize(ValidateRole constraintAnnotation) {}

    @Override
    public boolean isValid(Set<String> values, ConstraintValidatorContext constraintValidatorContext) {
        // Nếu là chuỗi rỗng thì bắt lại
        if (CollectionUtils.isEmpty(values))
            return false;
        // Kiểm tra tất cả giá trị xem có nằm trong enum ERole không
        return values.stream()
                .allMatch(value -> Arrays.stream(ERole.values())
                        .anyMatch(role -> role.name().equals(value)));
    }
}

