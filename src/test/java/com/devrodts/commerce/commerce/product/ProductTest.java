package com.devrodts.commerce.commerce.product;
import org.junit.Test;
import java.math.BigDecimal;

import com.devrodts.commerce.commerce.modules.product.domain.entity.ProductEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProductTest {

    @Test
    public void shouldCreateProductWithCorrectData() {
        ProductEntity product = new ProductEntity();
        product.setProductName("Server");
        product.setProductDescription("Apache");
        product.setProductPrice(BigDecimal.valueOf(2000, 10));
        
        assertNotNull(product);
        assertEquals("Server", product.getProductName());
        assertEquals("Apache", product.getProductDescription());
        assertEquals(BigDecimal.valueOf(2000, 10), product.getProductPrice());
    }
}
