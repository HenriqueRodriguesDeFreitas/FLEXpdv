package com.paulo.flexpdv.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public record CategoryResponseDto(@Schema(name = "id", example = "2c576a63-f88b-4a7d-a2d3-2503e7b7f824") UUID id,
                                  @Schema(name = "name", description = "Bebida") String name) {
}
