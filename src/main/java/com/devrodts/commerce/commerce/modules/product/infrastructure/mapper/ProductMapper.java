package com.devrodts.commerce.commerce.modules.product.infrastructure.mapper;

import com.devrodts.commerce.commerce.modules.product.application.dtos.CreateProductDTO;
import com.devrodts.commerce.commerce.modules.product.application.dtos.ProductDTO;
import com.devrodts.commerce.commerce.modules.product.domain.entity.ProductEntity;

import java.sql.Timestamp;
import java.time.Instant;

public class ProductMapper {
    
    public static ProductEntity toDomain(CreateProductDTO dto) {
        ProductEntity entity = new ProductEntity();
        entity.setProductName(dto.productName());
        entity.setProductDescription(dto.productDescription());
        entity.setProductPrice(dto.productPrice());
        entity.setProductQuantity(dto.productQuantity());
        entity.setCreationTime(Timestamp.from(Instant.now()));
        entity.setUpdateTime(Timestamp.from(Instant.now()));
        return entity;
    }
    
    public static ProductDTO toDTO(ProductEntity entity) {
        return new ProductDTO(
            entity.getProductId(),
            entity.getProductName(),
            entity.getProductDescription(),
            entity.getProductPrice(),
            entity.getProductQuantity(),
            null // productBrand is not available in the entity
        );
    }
}
