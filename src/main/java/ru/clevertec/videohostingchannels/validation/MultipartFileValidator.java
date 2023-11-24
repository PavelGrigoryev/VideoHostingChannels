package ru.clevertec.videohostingchannels.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class MultipartFileValidator implements ConstraintValidator<ValidMultipartFile, MultipartFile> {

    private List<String> availableContentTypes;

    @Override
    public void initialize(ValidMultipartFile constraintAnnotation) {
        availableContentTypes = List.of("image/png", "image/jpg", "image/jpeg");
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {
        String contentType = multipartFile.getContentType();
        return availableContentTypes.contains(contentType);
    }

}