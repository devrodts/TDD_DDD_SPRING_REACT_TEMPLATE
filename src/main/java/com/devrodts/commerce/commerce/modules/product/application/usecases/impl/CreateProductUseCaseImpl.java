package com.devrodts.commerce.commerce.modules.product.application.usecases.impl;

import org.springframework.stereotype.Service;

import com.devrodts.commerce.commerce.modules.product.application.dtos.CreateProductDTO;
import com.devrodts.commerce.commerce.modules.product.application.dtos.ProductResponseDTO;
import com.devrodts.commerce.commerce.modules.product.application.usecases.CreateProductUseCase;
import com.devrodts.commerce.commerce.modules.product.domain.entity.ProductEntity;
import com.devrodts.commerce.commerce.modules.product.infrastructure.repository.ProductRepository;

@Service
public class CreateProductUseCaseImpl implements CreateProductUseCase {
    
    private final ProductRepository productRepository;
    
    public CreateProductUseCaseImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    @Override
    public ProductResponseDTO execute(CreateProductDTO createProductDTO) {
        validateProductData(createProductDTO);
        
        ProductEntity productEntity = mapToEntity(createProductDTO);
        ProductEntity savedProduct = productRepository.save(productEntity);
        
        return mapToResponseDTO(savedProduct, createProductDTO.brand());
    }
    
    private void validateProductData(CreateProductDTO createProductDTO) {
        if (createProductDTO.name() == null || createProductDTO.name().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        
        if (createProductDTO.price() == null || createProductDTO.price().doubleValue() <= 0) {
            throw new IllegalArgumentException("Product price must be greater than zero");
        }
        
        if (createProductDTO.stock() < 0) {
            throw new IllegalArgumentException("Product stock cannot be negative");
        }
    }
    
    private ProductEntity mapToEntity(CreateProductDTO createProductDTO) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductName(createProductDTO.name());
        productEntity.setProductDescription(createProductDTO.description());
        productEntity.setProductPrice(createProductDTO.price());
        productEntity.setProductQuantity(createProductDTO.stock());
        productEntity.setProductImages(createProductDTO.images());
        return productEntity;
    }
    
    private ProductResponseDTO mapToResponseDTO(ProductEntity productEntity, String brand) {
        return new ProductResponseDTO(
            productEntity.getProductId(),
            productEntity.getProductName(),
            productEntity.getProductDescription(),
            productEntity.getProductPrice(),
            productEntity.getProductQuantity(),
            brand,
            productEntity.getProductImages()
        );
    }
} 