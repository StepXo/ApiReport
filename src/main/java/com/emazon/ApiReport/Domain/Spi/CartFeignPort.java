package com.emazon.ApiReport.Domain.Spi;
import com.emazon.ApiReport.Domain.Model.Cart;

public interface CartFeignPort {
    Cart getCart();

    void deleteItems();
}
