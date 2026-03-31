package com.paulo.flexpdv.service;

import com.paulo.flexpdv.dto.request.ProductCreateRequestDto;
import com.paulo.flexpdv.dto.request.ProductUpdateRequestDto;
import com.paulo.flexpdv.dto.response.ProductCreateResponseDto;
import com.paulo.flexpdv.exception.custom.EntityNotFoundException;
import com.paulo.flexpdv.exception.custom.ExistingEntityConflictException;
import com.paulo.flexpdv.mapper.ProductMapper;
import com.paulo.flexpdv.model.Product;
import com.paulo.flexpdv.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final static Logger log = LoggerFactory.getLogger(ProductService.class);

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Transactional
    public ProductCreateResponseDto create(ProductCreateRequestDto productRequest) {
        log.info("Starting product creation");

        var name = normalize(productRequest.name());
        var barcode = normalize(productRequest.barcode());

        log.debug("Normalized data - name: {}, barcode: {}", name, barcode);

        if (productRepository.findByBarcodeIgnoreCase(barcode).isPresent()) {
            log.warn("Product with barcode: {} already exists", barcode);
            throw new ExistingEntityConflictException("Product with this barcode already exists: " + barcode);
        }
        if (productRepository.findByNameIgnoreCase(name).isPresent()) {
            log.warn("Product with name: {} already exists", name);
            throw new ExistingEntityConflictException("Product with this name already exists: " + name);
        }

        Product product = productMapper.toEntity(productRequest);
        product.setName(name);
        product.setBarcode(barcode);

        try {
            var productSaved = productRepository.save(product);

            log.info("Product saved successfully with id: {}", productSaved.getId());
            return productMapper.toResponse(productSaved);
        } catch (DataIntegrityViolationException e) {
            log.error("Database wrror while saving product  with barcode: {}", barcode, e);
            throw new ExistingEntityConflictException("Database constraint violation");
        }
    }

    public ProductCreateResponseDto update(UUID productId, ProductUpdateRequestDto productRequest) {
        Product productRetuned = productRepository.findById(productId)
                .orElseThrow(()-> new EntityNotFoundException("Product does not exist"));

        return productMapper.toResponse(productRetuned);
    }

    private String normalize(String value) {
        return value == null ? null : value.trim();
    }

}
