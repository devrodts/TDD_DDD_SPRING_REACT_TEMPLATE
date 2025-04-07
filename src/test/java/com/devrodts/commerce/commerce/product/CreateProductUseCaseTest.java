package com.devrodts.commerce.commerce.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

import com.devrodts.commerce.commerce.modules.product.application.dtos.CreateProductDTO;
import com.devrodts.commerce.commerce.modules.product.application.dtos.ProductResponseDTO;
import com.devrodts.commerce.commerce.modules.product.application.usecases.impl.CreateProductUseCaseImpl;
import com.devrodts.commerce.commerce.modules.product.domain.entity.ProductEntity;
import com.devrodts.commerce.commerce.modules.product.infrastructure.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
class CreateProductUseCaseTest {
    
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CreateProductUseCaseImpl createProductUseCase;
    
    @Test
    void shouldCreateProductSuccessfully() {
        // Arrange
        CreateProductDTO createProductDTO = new CreateProductDTO(
            "Test Product",
            "Description of test product",
            new BigDecimal("99.99"),
            10,
            "Test Brand",
            new ArrayList<>()
        );
        
        ProductEntity savedEntity = new ProductEntity();
        savedEntity.setProductId(UUID.randomUUID());
        savedEntity.setProductName(createProductDTO.name());
        savedEntity.setProductDescription(createProductDTO.description());
        savedEntity.setProductPrice(createProductDTO.price());
        savedEntity.setProductQuantity(createProductDTO.stock());
        savedEntity.setProductImages(createProductDTO.images());
        
        when(productRepository.save(any(ProductEntity.class))).thenReturn(savedEntity);
        
        // Act
        ProductResponseDTO response = createProductUseCase.execute(createProductDTO);
        
        // Assert
        assertNotNull(response);
        assertEquals(savedEntity.getProductId(), response.id());
        assertEquals(createProductDTO.name(), response.name());
        assertEquals(createProductDTO.description(), response.description());
        assertEquals(createProductDTO.price(), response.price());
        assertEquals(createProductDTO.stock(), response.stock());
        assertEquals(createProductDTO.brand(), response.brand());
    }
    
    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        // Arrange
        CreateProductDTO createProductDTO = new CreateProductDTO(
            null,
            "Description of test product",
            new BigDecimal("99.99"),
            10,
            "Test Brand",
            new ArrayList<>()
        );
        
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            createProductUseCase.execute(createProductDTO);
        });
    }
}