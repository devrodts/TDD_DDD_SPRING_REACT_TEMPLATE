package com.devrodts.commerce.commerce.modules.product.infrastructure.repository;
import com.devrodts.commerce.commerce.modules.category.domain.entity.CategoryEntity;
import com.devrodts.commerce.commerce.modules.image.domain.entity.ImageEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class ProductRepository {

    private UUID productId;
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
    private int productQuantity;
    private String productBrand;
    private CategoryEntity category;
    private List<ImageEntity> images;




}
