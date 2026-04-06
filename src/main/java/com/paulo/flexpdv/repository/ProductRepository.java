package com.paulo.flexpdv.repository;

import com.paulo.flexpdv.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findByNameIgnoreCase(String nome);

    Optional<Product> findByBarcodeIgnoreCase(String barcode);

    Page<Product> findAllByOrderByNameAsc(Pageable pageable);
}
