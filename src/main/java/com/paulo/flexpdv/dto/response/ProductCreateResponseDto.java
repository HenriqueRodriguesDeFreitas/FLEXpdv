package com.paulo.flexpdv.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductCreateResponseDto(
        @Schema(description = "Product id", example = "a3f1c7e0-1a2b-4c5d-9e8f-123456789abc") UUID id,
        @Schema(description = "Product name", example = "Sabão em Pó 1kg - OMO")
        String name,
        @Schema(description = "Barcode", example = "00000000000000")
        String barcode,
        @Schema(description = "Cost price", example = "3.59")
        @Digits(integer = 10, fraction = 2) BigDecimal costPrice,
        @Schema(description = "Sale price", example = "5.99")
        @Digits(integer = 10, fraction = 2) BigDecimal salePrice,
        @Schema(description = "Stock", example = "1.00")
        @Digits(integer = 10, fraction = 2) BigDecimal stock,
        @Schema(description = "Is active", example = "true") boolean isActive,
        @Schema(description = "Stock control", example = "true") boolean stockControl) {
}
