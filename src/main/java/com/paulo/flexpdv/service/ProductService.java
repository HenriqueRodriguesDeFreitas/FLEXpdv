package com.paulo.flexpdv.service;

import com.paulo.flexpdv.dto.request.ProductCreateRequestDto;
import com.paulo.flexpdv.dto.response.ProductCreateResponseDto;
import com.paulo.flexpdv.exception.custom.ExistingEntityConflictException;
import com.paulo.flexpdv.mapper.ProductMapper;
import com.paulo.flexpdv.model.Product;
import com.paulo.flexpdv.repository.ProductRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Transactional
    public ProductCreateResponseDto create(ProductCreateRequestDto productRequest) {
        var name = normalize(productRequest.name());
        var barcode = normalize(productRequest.barcode());
        if (productRepository.findByBarcodeIgnoreCase(barcode).isPresent()) {
            throw new ExistingEntityConflictException("Product with this barcode already exists: " + barcode);
        }
        if (productRepository.findByNameIgnoreCase(name).isPresent()) {
            throw new ExistingEntityConflictException("Product with this name already exists: " + name);
        }

        Product product = productMapper.toEntity(productRequest);
        product.setName(name);
        product.setBarcode(barcode);

        try {
            var productSaved = productRepository.save(product);
            return productMapper.toResponse(productSaved);
        } catch (DataIntegrityViolationException e) {
            throw new ExistingEntityConflictException("Database constraint violation");
        }
    }

    private String normalize(String value) {
        return value == null ? null : value.trim();
    }

}
