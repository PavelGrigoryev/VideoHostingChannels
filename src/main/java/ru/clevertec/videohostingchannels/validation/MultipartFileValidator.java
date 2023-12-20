package ru.clevertec.videohostingchannels.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;
import ru.clevertec.videohostingchannels.validation.annotation.ValidMultipartFile;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MultipartFileValidator implements ConstraintValidator<ValidMultipartFile, MultipartFile> {

    private Pattern pattern;

    @Override
    public void initialize(ValidMultipartFile constraintAnnotation) {
        pattern = Pattern.compile(constraintAnnotation.regexp());
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {
        return Optional.ofNullable(multipartFile)
                .map(MultipartFile::getContentType)
                .map(pattern::matcher)
                .map(Matcher::matches)
                .orElse(true);
    }

}
