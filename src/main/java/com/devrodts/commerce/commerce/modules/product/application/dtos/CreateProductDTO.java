package com.devrodts.commerce.commerce.modules.product.application.dtos;

import java.math.BigDecimal;
import java.util.List;

import com.devrodts.commerce.commerce.modules.image.domain.entity.ImageEntity;

public record CreateProductDTO(
        String name,
        String description,
        BigDecimal price,
        int stock,
        String brand,
        List<ImageEntity> images
) {
}