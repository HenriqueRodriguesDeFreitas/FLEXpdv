package com.paulo.flexpdv.exception;

import com.paulo.flexpdv.dto.response.ErrorResponseDto;
import com.paulo.flexpdv.exception.custom.ExistingEntityConflictException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExistingEntityConflictException.class)
    public ResponseEntity<ErrorResponseDto> conflictEntityExisting(
            ExistingEntityConflictException ex) {
        ErrorResponseDto responseDto = toResponse(HttpStatus.CONFLICT,
                "Entity already exists.", ex);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseDto);
    }


    private static ErrorResponseDto toResponse(HttpStatus status, String erro, Exception e) {
        return new ErrorResponseDto(LocalDateTime.now().toString(), status.value(),
                erro, e.getMessage());
    }
}
