//package com.devrodts.commerce.commerce.product;
//
//import com.devrodts.commerce.commerce.modules.product.application.dtos.ProductDTO;
//import com.devrodts.commerce.commerce.modules.product.application.service.ProductService;
//import com.devrodts.commerce.commerce.modules.product.infrastructure.repository.ProductRepository;
//import org.junit.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.junit.Assert.assertNotNull;
//
//@ExtendWith(MockitoExtension.class)
//public class ProductServiceTest {
//
//    @Mock private ProductRepository productRepository;
//    @InjectMocks private ProductService productService;
//
//    @Test
//    public void shouldCreateAndSaveProduct(){
//        ProductCreateRequest request = new ProductCreateRequest("Notebook", "...", 1500.0, 10);
//        Product product = ProductMapper.toDomain(request);
//        when(productRepository.save(any())).thenReturn(product);
//        ProductDTO result = productService.createProduct(request);
//        assertNotNull(result);
//        verify(productRepository).save(any());
//    }
//    }
//
//}
