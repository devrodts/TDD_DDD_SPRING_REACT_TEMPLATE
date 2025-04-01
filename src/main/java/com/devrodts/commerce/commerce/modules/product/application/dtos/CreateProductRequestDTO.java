package com.devrodts.commerce.commerce.modules.product.application.dtos;

import java.math.BigDecimal;

public record CreateProductRequestDTO(
        String productName,
        String productDescription,
        BigDecimal productPrice,
        int productQuantity,
        String productBrand
) {
}