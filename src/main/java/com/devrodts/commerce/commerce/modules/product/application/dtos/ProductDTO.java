package com.devrodts.commerce.commerce.modules.product.application.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductDTO(
        UUID productId,
        String productName,
        String productDescription,
        BigDecimal productPrice,
        int productQuantity,
        String productBrand
) {
}
