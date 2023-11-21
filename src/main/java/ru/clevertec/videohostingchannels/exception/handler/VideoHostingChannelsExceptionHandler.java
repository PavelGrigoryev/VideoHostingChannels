package ru.clevertec.videohostingchannels.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.clevertec.videohostingchannels.exception.NotFoundException;
import ru.clevertec.videohostingchannels.exception.UniqueException;
import ru.clevertec.videohostingchannels.exception.model.ExceptionResponse;

@Slf4j
@ControllerAdvice
public class VideoHostingChannelsExceptionHandler {

    @ExceptionHandler(UniqueException.class)
    public ResponseEntity<ExceptionResponse> uniqueException(UniqueException exception) {
        return sendResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
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
