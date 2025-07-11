package com.poly.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.poly.dto.response.ApiResponse;

@RestControllerAdvice
public class GlobalException extends Exception {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidationExceptions(MethodArgumentNotValidException exception) {
        ErrorCode errorCode = ErrorCode.INPUT_VALID;

        FieldError fieldError = exception.getFieldError();
        if (fieldError != null) errorCode = ErrorCode.valueOf(fieldError.getDefaultMessage());

        ApiResponse<?> apiResponse = ApiResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();
        return ResponseEntity.status(errorCode.getStatusCode()).body(apiResponse);
    }

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse<?>> handlingRuntimeException(RuntimeException exception) {
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .code(ErrorCode.SYSTEM_INTERNAL_ERROR.getCode())
                .message(ErrorCode.SYSTEM_INTERNAL_ERROR.getMessage())
                .build();
        return ResponseEntity.status(ErrorCode.SYSTEM_INTERNAL_ERROR.getStatusCode())
                .body(apiResponse);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse<?>> handlingValidation(AppException exception) {
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .code(exception.getErrorCode().getCode())
                .message(exception.getErrorCode().getMessage())
                .build();
        return ResponseEntity.status(exception.getErrorCode().getStatusCode()).body(apiResponse);
    }
}
