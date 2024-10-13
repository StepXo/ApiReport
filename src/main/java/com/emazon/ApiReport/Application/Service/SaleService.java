package com.emazon.ApiReport.Application.Service;

import com.emazon.ApiReport.Application.Handler.SaleHandler;
import com.emazon.ApiReport.Application.Request.ItemAuxDto;
import com.emazon.ApiReport.Application.Response.ItemResponse;
import com.emazon.ApiReport.Application.Response.SaleResponse;
import com.emazon.ApiReport.Domain.Api.SaleServicePort;
import com.emazon.ApiReport.Domain.Model.Sale;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.emazon.ApiReport.Application.Utils.AppConstants.ZERO;

@Service
@RequiredArgsConstructor
@Transactional
public class SaleService {
    private final SaleServicePort saleServicePort;
    private final SaleHandler saleHandler;

    public SaleResponse sale() {
        Sale sale = saleServicePort.sale();
        SaleResponse saleResponse = saleHandler.toResponse(sale);
        List<ItemResponse> item = this.setItem(sale.getItems(),sale.getQuantity(),sale.getPrice());
        saleResponse.setItems(item);
        return saleResponse;
    }

    private List<ItemResponse> setItem(List<Long> items, List<Long> quantities, List<Double> prices) {
        List<ItemResponse> response = new ArrayList<>();
        for (int i = ZERO; i < items.size(); i++) {
            ItemResponse newItem = new ItemResponse();
            newItem.setId(items.get(i));
            newItem.setQuantity(quantities.get(i));
            newItem.setPrice(prices.get(i));
            response.add(newItem);
        }
        return response;
    }

}
