package com.emazon.ApiReport.Application.Response;

import com.emazon.ApiReport.Application.Request.BrandDto;
import com.emazon.ApiReport.Application.Request.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponse {
    private long id;
    private long quantity;
    private double price;
}

