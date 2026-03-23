package com.paulo.flexpdv.mapper;

import com.paulo.flexpdv.dto.request.ProductCreateRequestDto;
import com.paulo.flexpdv.dto.response.ProductCreateResponseDto;
import com.paulo.flexpdv.model.Product;
import java.math.BigDecimal;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-18T00:56:20-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.2 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toEntity(ProductCreateRequestDto request) {
        if ( request == null ) {
            return null;
        }

        Product product = new Product();

        product.setName( request.name() );
        product.setBarcode( request.barcode() );
        product.setCostPrice( request.costPrice() );
        product.setSalePrice( request.salePrice() );
        product.setStock( request.stock() );
        product.setStockControl( request.stockControl() );

        return product;
    }

    @Override
    public ProductCreateResponseDto toResponse(Product product) {
        if ( product == null ) {
            return null;
        }

        UUID id = null;
        String name = null;
        String barcode = null;
        BigDecimal costPrice = null;
        BigDecimal salePrice = null;
        BigDecimal stock = null;
        boolean stockControl = false;

        id = product.getId();
        name = product.getName();
        barcode = product.getBarcode();
        costPrice = product.getCostPrice();
        salePrice = product.getSalePrice();
        stock = product.getStock();
        stockControl = product.isStockControl();

        boolean isActive = false;

        ProductCreateResponseDto productCreateResponseDto = new ProductCreateResponseDto( id, name, barcode, costPrice, salePrice, stock, isActive, stockControl );

        return productCreateResponseDto;
    }
}
