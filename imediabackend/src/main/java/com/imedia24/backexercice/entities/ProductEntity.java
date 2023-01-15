package com.imedia24.backexercice.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table()
@Data @NoArgsConstructor @AllArgsConstructor
public class ProductEntity {
    @Id
    @Column(name = "sku", nullable = false)
    private String sku;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "stock")
    private String stockBySku;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @UpdateTimestamp
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

}
