package com.emazon.ApiReport.Infrastructure.Persistance.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document(collection = "sale")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleEntity {
    @Id
    String id;
    long userId;
    String email;
    List<ItemEntity> items;

    long total;
    String date;
}
