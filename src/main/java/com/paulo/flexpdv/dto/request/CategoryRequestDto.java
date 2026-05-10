package com.paulo.flexpdv.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryRequestDto(@Schema(name = "name",example = "Bebidas") @NotBlank @Size(min = 1, max = 50) String name) {
}
