package ru.clevertec.videohostingchannels.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ru.clevertec.videohostingchannels.validation.MultipartFileValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MultipartFileValidator.class)
public @interface ValidMultipartFile {

    String regexp() default "image/png|image/jpg|image/jpeg";

    String message() default "Acceptable avatar-images formats are only: {regexp}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
