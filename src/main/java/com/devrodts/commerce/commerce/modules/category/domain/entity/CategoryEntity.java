package com.devrodts.commerce.commerce.modules.category.domain.entity;
import com.devrodts.commerce.commerce.modules.product.domain.entity.ProductEntity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import java.util.UUID;
@Entity
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID categoryId;

    private String categoryName;

    private String categoryDescription;

    @ManyToOne
    @JoinColumn(name = "productId")
    private ProductEntity categoryProducts;

    private Timestamp createdAt;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductEntity> products;

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public ProductEntity getCategoryProducts() {
        return categoryProducts;
    }

    public void setCategoryProducts(ProductEntity categoryProducts) {
        this.categoryProducts = categoryProducts;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public BigDecimal getCategoryDiscount() {
        return categoryDiscount;
    }

    public void setCategoryDiscount(BigDecimal categoryDiscount) {
        this.categoryDiscount = categoryDiscount;
    }

    private Timestamp updatedAt;

    private BigDecimal categoryDiscount;

}
