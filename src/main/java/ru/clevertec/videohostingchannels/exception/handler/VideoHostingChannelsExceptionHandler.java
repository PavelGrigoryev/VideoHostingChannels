package ru.clevertec.videohostingchannels.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import ru.clevertec.videohostingchannels.exception.MultipartGetBytesException;
import ru.clevertec.videohostingchannels.exception.NotFoundException;
import ru.clevertec.videohostingchannels.exception.UniqueException;
import ru.clevertec.videohostingchannels.exception.model.ExceptionResponse;

@Slf4j
@ControllerAdvice
public class VideoHostingChannelsExceptionHandler {

    @ExceptionHandler(UniqueException.class)
    public ResponseEntity<ExceptionResponse> handleUniqueException(UniqueException exception) {
        return sendResponse(exception.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(NotFoundException exception) {
        return sendResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MultipartGetBytesException.class)
    public ResponseEntity<ExceptionResponse> handleMultipartGetBytesException(MultipartGetBytesException exception) {
        return sendResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ExceptionResponse> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException exception) {
        return sendResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ExceptionResponse> sendResponse(String message, HttpStatus httpStatus) {
        ExceptionResponse response = new ExceptionResponse(message);
        log.error(response.toString());
        return ResponseEntity.status(httpStatus).body(response);
    }

}
