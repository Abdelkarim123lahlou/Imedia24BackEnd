package com.imedia24.backexercice.mappers;

import com.imedia24.backexercice.dto.ProductDtoRequest;
import com.imedia24.backexercice.dto.ProductDtoResponse;
import com.imedia24.backexercice.entities.ProductEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductEntity productEntityFromProductDto(ProductDtoRequest productDtoRequest);
    ProductDtoResponse productDtoFromProductEntity(ProductEntity productEntity);
}
