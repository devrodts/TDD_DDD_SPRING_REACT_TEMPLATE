package com.devrodts.commerce.commerce.modules.product.application.usecases;

import org.springframework.validation.annotation.Validated;

import com.devrodts.commerce.commerce.modules.product.application.dtos.CreateProductDTO;
import com.devrodts.commerce.commerce.modules.product.application.dtos.ProductResponse;

public interface CreateProductUseCase {
    ProductResponse execute(@Validated CreateProductDTO createProductDTO);
}