package com.paulo.flexpdv.dto.response;

public record ErrorResponseDto(String timestamp,
                               int status,
                               String error,
                               String descricao) {
}
