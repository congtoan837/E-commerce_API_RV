package com.poly.dto.request;

import com.poly.validator.ValidatePassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SignupRequest {
    @Size(min = 6, message = "NAME_INVALID")
    @Pattern(regexp = "^(?!\\s*$)[A-Za-z ]+$", message = "NAME_INVALID")
    private String name;

    @Email(message = "EMAIL_INVALID")
    private String email;

    @Pattern(regexp = "^(\\+84|0)[0-9]{9}$", message = "PHONE_INVALID")
    private String phone;

    @Size(min = 6, message = "USERNAME_SHORT")
    @Pattern(regexp = "^[a-z0-9]+$", message = "USERNAME_INVALID")
    private String username;

    @ValidatePassword
    @Pattern(regexp = "^[A-Za-z0-9!@#$%^&*(),.?\":{}|<>]*$", message = "PASSWORD_INVALID")
    private String password;

    private String image;

    private String address;
}
