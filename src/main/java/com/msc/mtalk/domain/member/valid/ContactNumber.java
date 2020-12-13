package com.msc.mtalk.domain.member.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Documented
@Retention(RUNTIME)
@Target({FIELD, METHOD})
@Constraint(validatedBy =  ContactNumberValidator.class)
public @interface ContactNumber {
    String message() default "Invalid contact number (Size 12 ~ 13)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

