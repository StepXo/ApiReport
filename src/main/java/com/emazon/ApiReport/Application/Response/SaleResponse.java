package com.emazon.ApiReport.Application.Response;

import com.emazon.ApiReport.Domain.Model.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SaleResponse {
    long id;
    long userId;
    String email;
    List<ItemAuxDto> items;
    double total;
    String date;
}
