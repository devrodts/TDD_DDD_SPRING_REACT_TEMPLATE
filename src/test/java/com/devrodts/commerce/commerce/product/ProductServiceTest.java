package com.devrodts.commerce.commerce.product;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock private ProductRepository productRepository;
    @InjectMocks private ProductService productService;

    @Test
    void shouldCreateAndSaveProduct(){
        ProductCreateRequest request = new ProductCreateRequest("Notebook", "...", 1500.0, 10);
        Product product = ProductMapper.toDomain(request);
        when(productRepository.save(any())).thenReturn(product);
        ProductDTO result = productService.createProduct(request);
        assertNotNull(result);
        verify(productRepository).save(any());
    }
    }

}
