package com.imedia24.backexercice.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Stock {
    @Id
    private String sku;
    private int quantity;
    private LocalDate lastUpdate;
}