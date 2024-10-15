package com.emazon.ApiReport.Infrastructure.Persistance.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemEntity {

    @Id
    private long id;

    private String name;

    private String description;

    private long quantity;

    private long stock;

    private double price;

    @DBRef
    List<CategoryEntity> category;

    @DBRef
    private BrandEntity brand;

}
