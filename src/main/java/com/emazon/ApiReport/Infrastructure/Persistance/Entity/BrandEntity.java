package com.emazon.ApiReport.Infrastructure.Persistance.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandEntity {

    @Id
    private long id;

    private String name;

    private String description;


}
