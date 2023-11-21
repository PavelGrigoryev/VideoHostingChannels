package ru.clevertec.videohostingchannels.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.clevertec.videohostingchannels.exception.NotFoundException;
import ru.clevertec.videohostingchannels.exception.model.ExceptionResponse;

import java.util.Objects;

@Slf4j
@ControllerAdvice
public class VideoHostingChannelsExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionResponse> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        Throwable rootCause = exception.getRootCause();
        return sendResponse(Objects.nonNull(rootCause) ? rootCause.getMessage() : exception.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> notFoundException(NotFoundException exception) {
        return sendResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<ExceptionResponse> sendResponse(String message, HttpStatus httpStatus) {
        ExceptionResponse response = new ExceptionResponse(message);
        log.error(response.toString());
        return ResponseEntity.status(httpStatus).body(response);
    }

}
