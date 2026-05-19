package com.paulo.flexpdv.exception;

import com.paulo.flexpdv.dto.response.ErrorResponseDto;
import com.paulo.flexpdv.exception.custom.EntityAlreadyExistsException;
import com.paulo.flexpdv.exception.custom.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> existingEntityConflict(EntityAlreadyExistsException ex) {
        ErrorResponseDto response = toResponse(HttpStatus.CONFLICT,
                "Erro de entidade já cadastrada no banco de dados", ex);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    public ResponseEntity<ErrorResponseDto> entityNotFoundException(EntityNotFoundException ex) {
        ErrorResponseDto response = toResponse(HttpStatus.NOT_FOUND,
                "Erro de entidade não encontrada no banco de dados", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    private static ErrorResponseDto toResponse(HttpStatus status,
                                               String erro,
                                               Exception e) {
        return new ErrorResponseDto(LocalDateTime.now().toString(),
                status.value(),
                erro,
                e.getMessage());
    }
}
