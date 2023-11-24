package ru.clevertec.videohostingchannels.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MultipartFileValidator.class)
public @interface ValidMultipartFile {

    String message() default "Acceptable avatar-images formats are only: '.png', '.jpg' or .'jpeg'";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
