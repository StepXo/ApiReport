package com.emazon.ApiReport.Infrastructure.Adapters.Feign;


import com.emazon.ApiReport.Application.Response.CartResponse;
import com.emazon.ApiReport.Domain.Model.Cart;
import com.emazon.ApiReport.Domain.Model.Item;
import com.emazon.ApiReport.Domain.Spi.CartFeignPort;
import com.emazon.ApiReport.Domain.Spi.StockFeignPort;
import com.emazon.ApiReport.Infrastructure.Persistance.Mapper.CartMapper;
import com.emazon.ApiReport.Infrastructure.Persistance.Mapper.ItemMapper;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class StockFeign implements StockFeignPort {
    private final IStockFeign feign;
    private final ItemMapper itemMapper;


    @Override
    public List<Integer> updateStock(List<Item> itemsId) {
        return feign.buy(itemsId
                .stream()
                .map(itemMapper::toItemAuxDto)
                .toList())
                .getBody();
    }
}
