package com.devrodts.commerce.commerce.modules.product.domain.entity;

import com.devrodts.commerce.commerce.modules.category.domain.entity.CategoryEntity;
import com.devrodts.commerce.commerce.modules.image.domain.entity.ImageEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@Entity
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID productId;

    private String productName;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImageEntity> productImages;

    private int productQuantity;

    private String productDescription;

    private BigDecimal productPrice;

    private Timestamp creationTime;

    private Timestamp updateTime;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private CategoryEntity category;

}
