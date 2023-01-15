package com.imedia24.backexercice.web;

import com.imedia24.backexercice.dto.ProductDtoRequest;
import com.imedia24.backexercice.dto.ProductDtoResponse;
import com.imedia24.backexercice.entities.ProductEntity;
import com.imedia24.backexercice.services.ProductService;
import com.imedia24.backexercice.dto.ProductDtoRequest;
import com.imedia24.backexercice.dto.ProductDtoResponse;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@Api
@RestController
@RequestMapping(path = "/imediashop")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/productBySkus")
    public List<ProductDtoResponse> getProductDetails(@RequestParam(required = false) List<String> skus) {
       return productService.getProductsBySkus(skus) ;
    }
    @GetMapping(path = "/productBySku")
   public ProductDtoResponse getProductBySku(String sku){
        return productService.getProductBySku(sku);
    }

    @PostMapping(path = "/addproduct")

    public ProductDtoResponse addProduct(ProductDtoRequest productDtoRequest) {
        return productService.addProduct(productDtoRequest);
    }

    @PatchMapping("/product/{sku}")

    public ProductDtoResponse partialUpdateProduct(@PathVariable String sku, @RequestBody Map<String, Object> updates){
        return productService.partialUpdateProduct(sku ,  updates);
    }
}
