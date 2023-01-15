package com.imedia24.backexercice.repositories;

import com.imedia24.backexercice.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock,String> {
}
