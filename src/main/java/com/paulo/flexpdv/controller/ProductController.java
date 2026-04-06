package com.paulo.flexpdv.controller;

import com.paulo.flexpdv.docs.ErrorExamples;
import com.paulo.flexpdv.dto.request.ProductCreateRequestDto;
import com.paulo.flexpdv.dto.request.ProductUpdateRequestDto;
import com.paulo.flexpdv.dto.response.ErrorResponseDto;
import com.paulo.flexpdv.dto.response.ProductCreateResponseDto;
import com.paulo.flexpdv.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("products")
@Tag(name = "Product", description = "Operations related to product registration and modifications.")
public class ProductController {

    private final ProductService productService;
    private final String TYPE_JSON = "application/json";

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @Operation(summary = "Create new product", description = "Used to create new product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created!",
                    content = @Content(mediaType = TYPE_JSON,
                            schema = @Schema(implementation = ProductCreateResponseDto.class))),
            @ApiResponse(responseCode = "409", description = "Conflict: product with this already exists",
                    content = @Content(mediaType = TYPE_JSON,
                            schema = @Schema(implementation = ErrorResponseDto.class),
                            examples = {@ExampleObject(name = "product with this barcode already exists",
                                    value = ErrorExamples.ERROR_409_BARCODE),
                                    @ExampleObject(name = "product with this name already exists", value = ErrorExamples.ERROR_409_NAME)})),
    })
    public ResponseEntity<ProductCreateResponseDto> create(@RequestBody @Valid ProductCreateRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update product existing", description = "Used to update product existing")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product update sucessfully",
                    content = @Content(mediaType = TYPE_JSON,
                            schema = @Schema(implementation = ProductCreateResponseDto.class)))
    })
    public ResponseEntity<ProductCreateResponseDto> update(@PathVariable("id") UUID id, @RequestBody @Valid ProductUpdateRequestDto requestDto) {
        return ResponseEntity.ok(productService.update(id, requestDto));
    }

    @GetMapping
    @Operation(summary = "List all products", description = "Returns a paginated list of registered products. Supports sorting and pagination.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products retrieved sucessfully.")
    })
    public ResponseEntity<Page<ProductCreateResponseDto>> findAll(@ParameterObject @PageableDefault(size = 10, sort = "name") Pageable pageable) {
        return ResponseEntity.ok(productService.findAll(pageable));
    }
}
