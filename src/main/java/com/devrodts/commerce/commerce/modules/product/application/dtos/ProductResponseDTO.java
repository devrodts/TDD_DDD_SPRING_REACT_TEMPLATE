package com.devrodts.commerce.commerce.modules.product.application.dtos;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.devrodts.commerce.commerce.modules.image.domain.entity.ImageEntity;

public record ProductResponseDTO(
    UUID id,
    String name,
    String description,
    BigDecimal price,
    int stock,
    String brand,
    List<ImageEntity> images
) {
} 