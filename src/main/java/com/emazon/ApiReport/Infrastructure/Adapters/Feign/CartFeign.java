package com.emazon.ApiReport.Infrastructure.Adapters.Feign;



import com.emazon.ApiReport.Infrastructure.Persistance.Mapper.CartMapper;
import com.emazon.ApiReport.Application.Response.CartResponse;
import com.emazon.ApiReport.Domain.Model.Cart;
import com.emazon.ApiReport.Domain.Spi.CartFeignPort;
import com.emazon.ApiReport.Infrastructure.Persistance.Mapper.ItemMapper;
import com.emazon.ApiReport.Infrastructure.Utils.InfraConstants;
import lombok.AllArgsConstructor;

import javax.swing.plaf.basic.BasicComboBoxUI;

@AllArgsConstructor
public class CartFeign implements CartFeignPort {
    private final ICartFeign feign;
    private final CartMapper cartMapper;
    private final ItemMapper itemMapper;

    @Override
    public Cart getCart(){
        CartResponse cartResponse = feign
                .getCart(InfraConstants.CART_REQUEST,
                        InfraConstants.PAGE,
                        InfraConstants.SIZE).getBody();

        Cart cart = cartMapper.tocart(cartResponse);
        cart.setItem(cartResponse.getItem()
                .getContent()
                .stream()
                .map(itemMapper::toItem)
                .toList());
        return cart;
    }

    @Override
    public void deleteItems() {
        feign.buy();
    }


}
