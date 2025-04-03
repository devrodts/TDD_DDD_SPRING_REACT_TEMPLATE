package com.devrodts.commerce.commerce.modules.product.application.dtos;

import java.math.BigDecimal;

public record CreateProductDTO(
        String productName,
        String productDescription,
        BigDecimal productPrice,
        int productQuantity,
        String productBrand
) {
}