package com.paulo.flexpdv.dto.request;

import com.paulo.flexpdv.model.enums.UnitOfMeasure;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductCreateRequestDto(

        @Schema(description = "Product name", example = "Sabão em Pó 1kg - OMO")
        @NotBlank
        @Size(max = 150)
        String name,

        @Schema(description = "Barcode", example = "00000000000000")
        @NotBlank
        @Size(max = 20)
        String barcode,

        @Schema(description = "Cost price", example = "3.59")
        @NotNull
        @Digits(integer = 10, fraction = 2)
        BigDecimal costPrice,

        @Schema(description = "Sale price", example = "5.99")
        @NotNull
        @Digits(integer = 10, fraction = 2)
        BigDecimal salePrice,

        @Schema(description = "Stock quantity", example = "10.00")
        @NotNull
        @Digits(integer = 10, fraction = 2)
        BigDecimal stock,

        @Schema(description = "Enable stock control", example = "true")
        boolean stockControl,

        UnitOfMeasure unitOfMeasure 

) {
}