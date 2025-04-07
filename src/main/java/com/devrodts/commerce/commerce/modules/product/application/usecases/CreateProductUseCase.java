package com.devrodts.commerce.commerce.modules.product.application.usecases;

import com.devrodts.commerce.commerce.modules.product.application.dtos.CreateProductDTO;
import com.devrodts.commerce.commerce.modules.product.application.dtos.ProductResponseDTO;

public interface CreateProductUseCase {
    ProductResponseDTO execute(CreateProductDTO createProductDTO);
}