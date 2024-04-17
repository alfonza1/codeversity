package com.CourseDeliveryService.validators;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MinModuleCountValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MinModuleCount {
    String message() default "The course must have at least 4 modules";

    Class<?>[] groups() default {};

    Class[] payload() default {};

    int value() default 4;
}