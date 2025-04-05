package com.devrodts.commerce.commerce.modules.product.domain.entity;

import com.devrodts.commerce.commerce.modules.category.domain.entity.CategoryEntity;
import com.devrodts.commerce.commerce.modules.image.domain.entity.ImageEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID productId;

    private String name;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImageEntity> images;

    private int stock;

    private String description;

    private BigDecimal price;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return name;
    }

    public void setProductName(String name) {
        this.name = name;
    }

    public List<ImageEntity> getProductImages() {
        return images;
    }

    public void setProductImages(List<ImageEntity> images) {
        this.images = images;
    }

    public int getProductQuantity() {
        return stock;
    }

    public void setProductQuantity(int stock) {
        this.stock = stock;
    }

    public String getProductDescription() {
        return description;
    }

    public void setProductDescription(String description) {
        this.description = description;
    }

    public BigDecimal getProductPrice() {
        return price;
    }

    public void setProductPrice(BigDecimal price) {
        this.price = price;
    }

    public Timestamp getCreationTime() {
        return createdAt;
    }

    public void setCreationTime(Timestamp createdAt) {
        this.createdAt = createdAt;
    }


    public Timestamp getUpdateTime() {
        return updatedAt;
    
    }


    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    private Timestamp updateTime;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private CategoryEntity category;



}
