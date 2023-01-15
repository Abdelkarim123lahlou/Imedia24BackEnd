package com.imedia24.backexercice.services;

import com.imedia24.backexercice.dto.ProductDtoResponse;
import com.imedia24.backexercice.entities.ProductEntity;
import com.imedia24.backexercice.mappers.ProductMapper;
import com.imedia24.backexercice.repositories.ProductRpository;
import com.imedia24.backexercice.repositories.StockRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest1 {
    @Autowired
    private ProductService productService;
    @Mock
    private ProductRpository productRepository;

    @Mock
    private ProductMapper productMapper;

    @Mock
    private StockRepository stockRepository;

    @Test
    void getProductsBySkus() {
        // Arrange
        List<String> skus = Arrays.asList("123456", "789012");
        ProductEntity product1 = new ProductEntity();
        product1.setSku("123456");
        product1.setName("Product 1");
        ProductEntity product2 = new ProductEntity();
        product2.setSku("789012");
        product2.setName("Product 2");
        List<ProductEntity> products = Arrays.asList(product1, product2);
        when(productRepository.findBySkuIn(skus)).thenReturn(products);
        ProductDtoResponse productDto1 = new ProductDtoResponse();
        productDto1.setSku("123456");
        productDto1.setName("Product 1");
        ProductDtoResponse productDto2 = new ProductDtoResponse();
        productDto2.setSku("789012");
        productDto2.setName("Product 2");
        List<ProductDtoResponse> expectedProducts = Arrays.asList(productDto1, productDto2);
        when(productMapper.productDtoFromProductEntity(product1)).thenReturn(productDto1);
        when(productMapper.productDtoFromProductEntity(product2)).thenReturn(productDto2);

        // Act
        List<ProductDtoResponse> result = productService.getProductsBySkus(skus);

        // Assert
        assertEquals(expectedProducts, result);
        verify(productRepository, times(1)).findBySkuIn(skus);
        verify(productMapper, times(1)).productDtoFromProductEntity(product1);
        verify(productMapper, times(1)).productDtoFromProductEntity(product2);
    }

    @Test
    void partialUpdateProduct() {
        // Arrange
        String sku = "123456";
        ProductEntity product = new ProductEntity();
        product.setSku(sku);
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setPrice(new BigDecimal(12.34));
        when(productRepository.findById(sku)).thenReturn(Optional.of(product));
        Map<String, Object> updates = new HashMap<>();
        updates.put("name", "Updated Product");
        updates.put("description", "Updated Description");
        updates.put("price", new BigDecimal(23.45));
        ProductEntity updatedProduct = new ProductEntity();
        updatedProduct.setSku(sku);
        updatedProduct.setName("Updated Product");
        updatedProduct.setDescription("Updated Description");
        updatedProduct.setPrice(new BigDecimal(23.45));
        when(productRepository.save(product)).thenReturn(updatedProduct);
        ProductDtoResponse expectedProduct = new ProductDtoResponse();
        expectedProduct.setSku(sku);
        expectedProduct.setName("Updated Product");
        expectedProduct.setDescription("Updated Description");
        expectedProduct.setPrice(new BigDecimal(23.45));
        when(productMapper.productDtoFromProductEntity(updatedProduct)).thenReturn(expectedProduct);

        // Act
        ProductDtoResponse result = productService.partialUpdateProduct(sku, updates);

        // Assert
        assertEquals(expectedProduct, result);
        verify(productRepository, times(1)).findById(sku);
        verify(productRepository, times(1)).save(product);
        verify(productMapper, times(1)).productDtoFromProductEntity(updatedProduct);
    }


}