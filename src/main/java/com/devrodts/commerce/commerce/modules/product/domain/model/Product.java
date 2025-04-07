package com.devrodts.commerce.commerce.modules.product.domain.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class Product {
    private UUID id;
    private String name;
    private String description;
    private Money price;
    private StockQuantity stock;
    private Brand brand;
    private List<Image> images;
    private Audit audit; 


    public static Product create(String name, String description, Money price, StockQuantity stock, Brand brand, List<Image> images){
        validateCreationParameters(name, price, stock);

        return new Product(
                UUID.randomUUID(),
                name,
                description,
                price,
                stock,
                brand,
                images,
                Audit.createdNow()
        );
    }

    public record ProductId(UUID value){
        public static ProductId random(){
            return new ProductId(UUID.randomUUID());

        }
    }


    public record Money(BigDecimal amount, Currency currency){
        public Money {
            if(amount.compareTo(BigDecimal.ZERO) <= 0){
                throw new IllegalArgumentException("Amount must be positive");
            }
        }
    }
}
