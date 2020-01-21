package pn.user_system.annotations.password;

import org.springframework.stereotype.Component;
import pn.user_system.constants.TextConstant;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Component
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
    String message() default TextConstant.INVALID_PASSWORD_FORMAT;

    int minLength() default 6;

    int maxLength() default 40;

    boolean containsDigit() default false;

    boolean containsLowerCase() default false;

    boolean containsUpperCase() default false;

    boolean containsSpecialSymbol() default false;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payloads() default {};
}
