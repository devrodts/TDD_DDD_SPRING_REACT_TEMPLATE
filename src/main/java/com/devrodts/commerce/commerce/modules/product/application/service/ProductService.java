package com.devrodts.commerce.commerce.modules.product.application.service;

import com.devrodts.commerce.commerce.modules.product.application.dtos.CreateProductRequestDTO;
import com.devrodts.commerce.commerce.modules.product.application.dtos.ProductDTO;
import com.devrodts.commerce.commerce.modules.product.domain.entity.ProductEntity;
import com.devrodts.commerce.commerce.modules.product.infrastructure.mapper.ProductMapper;
import com.devrodts.commerce.commerce.modules.product.infrastructure.repository.ProductRepository;
import org.springframework.stereotype.Service;


@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDTO createProduct(CreateProductRequestDTO request){
        ProductEntity product = ProductMapper.toDomain(request);
        productRepository.save(product);
        return ProductMapper.toDTO(product);
    }
}