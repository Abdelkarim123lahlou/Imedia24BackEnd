package com.imedia24.backexercice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@AllArgsConstructor @NoArgsConstructor
public class ProductDtoResponse {


    private String sku;


    private String name;


    private String description;


    private BigDecimal price;


    private Date createdAt;


    private Date updatedAt;
}
