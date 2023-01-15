package com.imedia24.backexercice.repositories;

import com.imedia24.backexercice.entities.ProductEntity;
import com.imedia24.backexercice.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRpository extends JpaRepository<ProductEntity,String> {
    List<ProductEntity> findBySkuIn(List<String> skus);
}
