package com.hendisantika.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-reset-password
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/04/22
 * Time: 08.30
 */
@Data
public class PasswordForgot {
    @NotEmpty(message = "{EMAIL_REQUIRED}")
    @Email(message = "{NOT_VALID_EMAIL}")
    private String email;
}
