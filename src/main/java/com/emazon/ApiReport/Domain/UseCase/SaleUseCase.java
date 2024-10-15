package com.emazon.ApiReport.Domain.UseCase;

import com.emazon.ApiReport.Domain.Api.SaleServicePort;
import com.emazon.ApiReport.Domain.Exeptions.ItemNotFoundException;
import com.emazon.ApiReport.Domain.Exeptions.QuantityIsNotEnough;
import com.emazon.ApiReport.Domain.Model.Item;
import com.emazon.ApiReport.Domain.Model.Sale;
import com.emazon.ApiReport.Domain.Model.Cart;
import com.emazon.ApiReport.Domain.Spi.*;
import com.emazon.ApiReport.Domain.Utils.Validations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.emazon.ApiReport.Domain.Utils.DomConstants.ZERO;

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

        String user = userJwt.extractUserId();
        Validations.validate(user);
        long userId = Long.parseLong(user);
        String email = userJwt.extractEmail(userId);

        Cart cart = cartFeignPort.getCart();
        Validations.validate(cart,email);

        List<Integer> stockValidation = stockFeignPort.updateStock(cart.getItem());
        List<Long> stock = Validations.validate(cart, stockValidation);
        if (!stock.isEmpty()) {
            String errorMessage = transactionFeignPort.getErrorMessage(stock);
            throw new QuantityIsNotEnough(errorMessage);
        }
        List<Item> items =  cart.getItem();

        Sale sale = Sale.builder()
                .setUserId(userId)
                .setEmail(email)
                .setItems(items)
                .setTotal(cart.getTotal())
                .setDate(LocalDate.now().toString())
                .build();

        cartFeignPort.deleteItems(); //to do: User could not buy all items

        return salePersistencePort.saveSale(sale);
    }

}
