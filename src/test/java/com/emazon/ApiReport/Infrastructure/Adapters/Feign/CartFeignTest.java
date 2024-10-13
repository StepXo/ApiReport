package com.emazon.ApiReport.Infrastructure.Adapters.Feign;

import com.emazon.ApiReport.Application.Request.ItemAuxDto;
import com.emazon.ApiReport.Infrastructure.Persistance.Mapper.CartMapper;
import com.emazon.ApiReport.Application.Response.CartResponse;
import com.emazon.ApiReport.Domain.Model.Cart;
import com.emazon.ApiReport.Domain.Model.Item;
import com.emazon.ApiReport.Infrastructure.Persistance.Mapper.ItemMapper;
import com.emazon.ApiReport.Infrastructure.Utils.InfraConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

class CartFeignTest {

    @Mock
    private ICartFeign feign;

    @Mock
    private CartMapper cartMapper;

    @Mock
    private ItemMapper itemMapper;

    @InjectMocks
    private CartFeign cartFeign;

    private CartResponse cartResponse;
    private Cart cart;
    private List<ItemAuxDto> itemList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        cartResponse = new CartResponse();
        itemList = Arrays.asList(
                setItemAuxDto(1L, "item1", 2, 100.0),
                setItemAuxDto(2L, "item2", 3, 150.0));

        cartResponse.setItem(new PageImpl<>(itemList));
        cart = new Cart();
    }
    private ItemAuxDto setItemAuxDto(long index, String name, long quantity, double price) {
        ItemAuxDto item = new ItemAuxDto();
        item.setId(index);
        item.setName(name);
        item.setQuantity(quantity);
        item.setPrice(price);
        return item;
    }

    @Test
    void shouldReturnCartWhenGetCartIsCalled() {
        when(feign.getCart(InfraConstants.CART_REQUEST, InfraConstants.PAGE, InfraConstants.SIZE))
                .thenReturn(ResponseEntity.ok(cartResponse));
        when(cartMapper.tocart(cartResponse)).thenReturn(cart);
        when(itemMapper.toItem(cartResponse.getItem().getContent().get(0))).thenReturn(new Item());

        Cart result = cartFeign.getCart();

        assertEquals(cart, result);
    }

    @Test
    void shouldCallBuyWhenDeleteItemsIsCalled() {
        cartFeign.deleteItems();

        verify(feign).buy();
    }
}
