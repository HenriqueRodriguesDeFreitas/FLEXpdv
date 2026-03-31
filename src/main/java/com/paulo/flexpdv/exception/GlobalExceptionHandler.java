package com.paulo.flexpdv.exception;

import com.paulo.flexpdv.dto.response.ErrorResponseDto;
import com.paulo.flexpdv.exception.custom.EntityNotFoundException;
import com.paulo.flexpdv.exception.custom.ExistingEntityConflictException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ExistingEntityConflictException.class)
    public ResponseEntity<ErrorResponseDto> conflictEntityExisting(
            ExistingEntityConflictException ex) {
        log.warn("Entity already exists: {}", ex.getMessage());
        var responseDto = toResponse(HttpStatus.CONFLICT,
                "Entity already exists.", ex);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseDto);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> notFoundException(EntityNotFoundException ex) {
        log.warn("Entity not found: {}", ex.getMessage());
        var responseDto = toResponse(HttpStatus.NOT_FOUND, "Entity not found.", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> illegalArgument(IllegalArgumentException ex) {
        log.warn("Invalid argument: {}", ex.getMessage());
        var responseDto = toResponse(HttpStatus.BAD_REQUEST,
                "Argument invalid", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> genericException(Exception ex) {

        log.error("Unexpected error", ex);

        var responseDto = toResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                "Unexpected error", ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }


    private static ErrorResponseDto toResponse(HttpStatus status, String erro, Exception e) {
        return new ErrorResponseDto(LocalDateTime.now().toString(), status.value(),
                erro, e.getMessage());
    }
}
