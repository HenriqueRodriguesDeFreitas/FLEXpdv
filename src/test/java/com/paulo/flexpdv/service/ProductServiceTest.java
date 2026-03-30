package com.paulo.flexpdv.service;

import com.paulo.flexpdv.dto.request.ProductCreateRequestDto;
import com.paulo.flexpdv.dto.response.ProductCreateResponseDto;
import com.paulo.flexpdv.exception.custom.ExistingEntityConflictException;
import com.paulo.flexpdv.mapper.ProductMapper;
import com.paulo.flexpdv.model.Product;
import com.paulo.flexpdv.model.enums.UnitOfMeasure;
import com.paulo.flexpdv.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductMapper productMapper;

    private ProductCreateResponseDto createResponseDto;
    private ProductCreateRequestDto createRequestDto;
    private Product product;

    @BeforeEach
    void setUp() {
        createRequestDto = new ProductCreateRequestDto("product1",
                "00000000000000", BigDecimal.ZERO,
                BigDecimal.ZERO, BigDecimal.ZERO, true, UnitOfMeasure.UN);

        product = new Product(UUID.randomUUID(),
                "product1",
                "00000000000000",
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                true,
                true,
                UnitOfMeasure.UN
        );
    }

    @Test
    void create_whenValidRequest_shouldReturnCreatedProduct() {
        when(productRepository.findByBarcodeIgnoreCase(anyString())).thenReturn(Optional.empty());
        when(productRepository.findByNameIgnoreCase(anyString())).thenReturn(Optional.empty());
        when(productMapper.toEntity(createRequestDto)).thenReturn(product);
        when(productRepository.save(any(Product.class))).thenReturn(product);
        when(productMapper.toResponse(any(Product.class))).thenAnswer(invocation -> {
            Product p = invocation.getArgument(0);
            return convertObjectToResponseDto(p);
        });

        createResponseDto = productService.create(createRequestDto);

        assertNotNull(createResponseDto, "Response should not be null");
        assertEquals(product.getId(), createResponseDto.id(), "Product id should match");
        assertEquals(product.getName(), createResponseDto.name(), "Product name should match");
        assertEquals(product.getBarcode(), createResponseDto.barcode(), "Product barcode should match");
    }

    @Test
    void create_whenBarcodeAlreadyExists_shouldThrowExistingEntityConflictException() {
        when(productRepository.findByBarcodeIgnoreCase(anyString())).thenReturn(Optional.of(product));

        var exception = assertThrows(ExistingEntityConflictException.class, () -> productService.create(createRequestDto));

        assertEquals("Product with this barcode already exists: " + product.getBarcode(), exception.getMessage());
    }



    private ProductCreateResponseDto convertObjectToResponseDto(Product p) {
        return new ProductCreateResponseDto(p.getId(), p.getName(), p.getBarcode(), p.getCostPrice(), p.getSalePrice(), p.getStock(), p.isActive(), p.isStockControl(), p.getUnitOfMeasure());
    }
}