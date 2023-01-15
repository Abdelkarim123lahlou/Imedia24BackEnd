package com.imedia24.backexercice.services;

import com.imedia24.backexercice.dto.ProductDtoRequest;
import com.imedia24.backexercice.dto.ProductDtoResponse;
import com.imedia24.backexercice.entities.ProductEntity;
import com.imedia24.backexercice.entities.Stock;
import com.imedia24.backexercice.exceptions.BadRequestException;
import com.imedia24.backexercice.exceptions.InternalServerErrorException;
import com.imedia24.backexercice.exceptions.ResourceNotFoundException;
import com.imedia24.backexercice.mappers.ProductMapper;
import com.imedia24.backexercice.repositories.ProductRpository;
import com.imedia24.backexercice.repositories.StockRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRpository productRpository;
    private ProductMapper productMapper;
    private StockRepository stockRepository;


//injection of dependancies by a constructor
    public ProductServiceImpl(ProductRpository productRpository,ProductMapper productMapper, StockRepository stockRepository) {
        this.productRpository = productRpository;
        this.productMapper=productMapper;
        this.stockRepository = stockRepository;
     }

    /***
     * get list of product details by list of SKUs
     * @param list of skus
     * @return listOf products of skus given
     */
    @Override
    public List<ProductDtoResponse> getProductsBySkus(List<String> skus) {
        if (skus == null || skus.isEmpty()) {
            throw new BadRequestException("SKUs are missing in the request.");
        }
        try {
            List<ProductEntity> productsBySkus = productRpository.findBySkuIn(skus);
            return productsBySkus.stream()
                    .map(product ->productMapper.productDtoFromProductEntity(product))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new InternalServerErrorException("Error while fetching product details by skus", e);
        }
    }
    //First, we will need to create a new migration script using Flyway's syntax,
    // which will add the new column 'stock' to the product table. (ALTER TABLE product ADD COLUMN stock INTEGER)
    //Run the migration script using Flyway command-line tool or the Flyway Maven plugin to update the database schema.
    //    ./flyway migrate
    //
    /***
     * @param sku
     * @return
     */
    @Override
    public ProductDtoResponse getProductBySku(String sku) {
        Optional<ProductEntity> product = productRpository.findById(sku);
        Optional<Stock> stock = stockRepository.findById(sku);
        if (!product.isPresent())
            throw new ResourceNotFoundException("Product not found with sku: " + sku);
        if (!stock.isPresent()) {
            throw new ResourceNotFoundException("Stock not found with sku: " + sku);
        }else {
            product.get().setStockBySku(sku);}
        return productMapper.productDtoFromProductEntity(product.get());
    }


    /***
     * Add product to data base
     * @param productDtoRequest
     * @return saved productEntity
     */
    @Override
    public ProductDtoResponse addProduct(ProductDtoRequest productDtoRequest) {
        ProductEntity productToAdd = productMapper.productEntityFromProductDto(productDtoRequest);
        ProductEntity productSaved = productRpository.save(productToAdd);
        return productMapper.productDtoFromProductEntity(productSaved);
    }

    @Override
    public ProductDtoResponse partialUpdateProduct(String sku, Map<String, Object> updates) {
       Optional<ProductEntity> productEntity = productRpository.findById(sku);
        if (productEntity == null) {
            throw new ResourceNotFoundException("Product not found with sku: " + sku);
        }
        if (updates.containsKey("name")) {
            productEntity.get().setName((String) updates.get("name"));
        }
        if (updates.containsKey("description")) {
            productEntity.get().setDescription((String) updates.get("description"));
        }
        if (updates.containsKey("price")) {
            productEntity.get().setPrice((BigDecimal) updates.get("price"));
        }
       ProductEntity productSaved = productRpository.save(productEntity.get());
        return productMapper.productDtoFromProductEntity(productSaved)  ;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
