package com.emazon.ApiReport.Domain.UseCase;

import com.emazon.ApiReport.Domain.Api.SaleServicePort;
import com.emazon.ApiReport.Domain.Exeptions.QuantityIsNotEnough;
import com.emazon.ApiReport.Domain.Model.Item;
import com.emazon.ApiReport.Domain.Model.Sale;
import com.emazon.ApiReport.Domain.Model.Cart;
import com.emazon.ApiReport.Domain.Spi.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SaleUseCase implements SaleServicePort {
    private final SalePersistencePort salePersistencePort;
    private final UserJwtPort userJwt;
    private final StockFeignPort stockFeignPort;
    private final CartFeignPort cartFeignPort;
    private final TransactionFeignPort transactionFeignPort;

    public SaleUseCase(SalePersistencePort salePersistencePort, UserJwtPort userJwt, StockFeignPort stockFeignPort, CartFeignPort cartFeignPort, TransactionFeignPort transactionFeignPort) {
        this.salePersistencePort = salePersistencePort;
        this.userJwt = userJwt;
        this.stockFeignPort = stockFeignPort;
        this.cartFeignPort = cartFeignPort;
        this.transactionFeignPort = transactionFeignPort;
    }


    @Override
    public Sale sale() {
        Sale sale = new Sale();
        List<Long> itemsNotFound = new ArrayList<>();
        List<Long> itemsWithNoStock = new ArrayList<>();

        String user = userJwt.extractUserId();
        long userId = Long.parseLong(user);

        String email = userJwt.extractEmail(userId);
        Cart cart = cartFeignPort.getCart();
        List<Long> items =  new ArrayList<>();
        List<Long> quantities =  new ArrayList<>();
        List<Double> prices =  new ArrayList<>();

        List<Integer> stockValidation = stockFeignPort.updateStock(cart.getItem());

        for (int i = 0; i <= stockValidation.size()  ; i++) {

            if (stockValidation.get(i) == 1) {
                itemsNotFound.add(cart.getItem().get(i).getId());
            }
            if (stockValidation.get(i) == 2) {
                itemsWithNoStock.add(cart.getItem().get(i).getId());
            }
        }
        if (itemsNotFound.isEmpty()) {
            throw new RuntimeException("The following items were not found in stock: " + itemsNotFound);
        }
        if (!itemsWithNoStock.isEmpty()) {
            String errorMessage = transactionFeignPort.getErrorMessage(itemsWithNoStock);
            throw new QuantityIsNotEnough(errorMessage);
        }

        sale.setUserId(userId);
        sale.setEmail(email);

        sale.setItems(items);
        sale.setQuantity(quantities);
        sale.setPrice(prices);

        sale.setTotal(cart.getTotal());
        sale.setDate(LocalDate.now().toString());

        cartFeignPort.deleteItems();
        return salePersistencePort.saveSale(sale);
    }
}
