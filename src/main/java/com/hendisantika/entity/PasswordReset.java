package com.hendisantika.entity;

import com.hendisantika.validator.PasswordConfirmation;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-reset-password
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/04/22
 * Time: 08.31
 */
@Data
@PasswordConfirmation(
        password = "password",
        confirmPassword = "confirmPassword",
        message = "PASSWORDS_NOT_EQUAL"
)
public class PasswordReset {
    @NotEmpty
    private String password;

    @NotEmpty
    private String confirmPassword;

    @NotEmpty
    private String token;
}
