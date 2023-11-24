package ru.clevertec.videohostingchannels.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ru.clevertec.videohostingchannels.validation.PageableValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PageableValidator.class)
public @interface ValidPageable {

    String regexp() default "name|mainLanguage|category";

    String message() default "Acceptable pageable sort orders are only: {regexp}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
