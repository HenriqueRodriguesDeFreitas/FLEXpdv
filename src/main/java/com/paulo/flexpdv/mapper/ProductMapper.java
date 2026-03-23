package com.paulo.flexpdv.mapper;

import com.paulo.flexpdv.dto.request.ProductCreateRequestDto;
import com.paulo.flexpdv.dto.response.ProductCreateResponseDto;
import com.paulo.flexpdv.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "barcode", target = "barcode")
    @Mapping(source = "costPrice", target = "costPrice")
    @Mapping(source = "salePrice", target = "salePrice")
    @Mapping(source = "stock", target = "stock")
    @Mapping(source = "stockControl", target = "stockControl")
    Product toEntity(ProductCreateRequestDto request);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "barcode", target = "barcode")
    @Mapping(source = "costPrice", target = "costPrice")
    @Mapping(source = "salePrice", target = "salePrice")
    @Mapping(source = "stock", target = "stock")
    @Mapping(source = "stockControl", target = "stockControl")
    ProductCreateResponseDto toResponse(Product product);
}
