package com.emazon.ApiReport.Domain.Spi;

import com.emazon.ApiReport.Domain.Model.Item;

import java.util.List;

public interface StockFeignPort {
    List<Integer>  updateStock(List<Item> items);
}
