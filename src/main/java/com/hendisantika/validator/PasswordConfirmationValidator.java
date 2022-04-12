package com.hendisantika.validator;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-reset-password
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/04/22
 * Time: 08.32
 */
public class PasswordConfirmationValidator implements ConstraintValidator<PasswordConfirmation, Object> {
    private String password;
    private String confirmPassword;

    @Override
    public void initialize(PasswordConfirmation constraintAnnotation) {
        this.password = constraintAnnotation.password();
        this.confirmPassword = constraintAnnotation.confirmPassword();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Object passwordValue = new BeanWrapperImpl(o).getPropertyValue(password);
        Object confirmPasswordValue = new BeanWrapperImpl(o).getPropertyValue(confirmPassword);
        if (passwordValue != null) {
            return passwordValue.equals(confirmPasswordValue);
        }
        return confirmPasswordValue == null;
    }
}
