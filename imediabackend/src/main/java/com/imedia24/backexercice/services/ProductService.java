package com.imedia24.backexercice.services;

import com.imedia24.backexercice.dto.ProductDtoRequest;
import com.imedia24.backexercice.dto.ProductDtoResponse;
import com.imedia24.backexercice.entities.ProductEntity;
import com.imedia24.backexercice.dto.ProductDtoRequest;
import com.imedia24.backexercice.dto.ProductDtoResponse;

import java.util.List;
import java.util.Map;

public interface ProductService {

    public List<ProductDtoResponse> getProductsBySkus(List<String> skus);

    public ProductDtoResponse getProductBySku(String sku);

    public ProductDtoResponse addProduct (ProductDtoRequest productDtoRequest);

    public ProductDtoResponse partialUpdateProduct(String sku, Map<String, Object> updates);
}
