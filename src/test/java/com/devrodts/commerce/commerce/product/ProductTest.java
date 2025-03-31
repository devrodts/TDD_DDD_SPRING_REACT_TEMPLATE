package com.devrodts.commerce.commerce.product;
import org.junit.Test;
import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Test
void shouldCreateProductWithCorrectData(){
    Product product = new Product("Server", "Apache", BigDecimal.valueOf((2000), 10));
    assertNotNull(product);
    assertEquals("Server", product.getName());
    assertEquals("Apache", product.getDescription());
    assertEquals(BigDecimal.valueOf((2000), 10), product.getPrice());

}
