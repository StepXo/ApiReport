package com.emazon.ApiReport.Application.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemAuxDto {
    private long id;
    private String name;
    private String description;
    private long quantity;
    private long stock;
    private double price;
    private List<CategoryDto> category;
    private BrandDto brand;
}

