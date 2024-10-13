package com.emazon.ApiReport.Application.Response;

import com.emazon.ApiReport.Application.Request.ItemAuxDto;
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
    List<ItemResponse> items;
    Double total;
    String date;
}
