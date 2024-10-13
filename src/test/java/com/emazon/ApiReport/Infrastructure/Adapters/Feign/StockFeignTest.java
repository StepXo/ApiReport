package com.emazon.ApiReport.Infrastructure.Adapters.Feign;

import com.emazon.ApiReport.Application.Request.ItemAuxDto;
import com.emazon.ApiReport.Domain.Model.Item;
import com.emazon.ApiReport.Infrastructure.Persistance.Mapper.ItemMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StockFeignTest {

    @Mock
    private IStockFeign stockFeign;
    @Mock
    private ItemMapper itemMapper;
    @InjectMocks
    private StockFeign stockFeignClient;

    private ItemAuxDto itemAuxDto;
    private List<Item> items;
    private Item item;
    private List<Integer> expectedResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        items = new ArrayList<>();
        expectedResponse = new ArrayList<>();
        item = new Item();
        item.setId(1);
        items.add(item);
        itemAuxDto = setItemAuxDto(1, "Item 1", 10, 100.0);

    }
    private ItemAuxDto setItemAuxDto(long index, String name, long quantity, double price) {
        ItemAuxDto newItem = new ItemAuxDto();
        newItem.setId(index);
        newItem.setName(name);
        newItem.setQuantity(quantity);
        newItem.setPrice(price);
        return newItem;
    }

    @Test
    void shouldUpdateStockSuccessfully() {
        expectedResponse = List.of(1, 2, 3);

        when(itemMapper.toItemAuxDto(item)).thenReturn(itemAuxDto);
        when(stockFeign.buy(List.of(itemAuxDto))).thenReturn(ResponseEntity.ok(expectedResponse));

        List<Integer> result = stockFeignClient.updateStock(items);

        assertEquals(expectedResponse, result);
    }

    @Test
    void shouldReturnEmptyListWhenNoItems() {
        items = new ArrayList<>();
        expectedResponse = new ArrayList<>();

        ResponseEntity<List<Integer>> responseEntity = ResponseEntity.ok(expectedResponse);
        when(stockFeign.buy(new ArrayList<>())).thenReturn(responseEntity);

        List<Integer> result = stockFeignClient.updateStock(items);

        // Assert
        assertEquals(expectedResponse, result);
    }

    @Test
    void shouldHandleFeignErrorGracefully() {

        when(itemMapper.toItemAuxDto(item)).thenReturn(itemAuxDto);
        when(stockFeign.buy(List.of(itemAuxDto)))
                .thenReturn(ResponseEntity.internalServerError().build());

        List<Integer> result = stockFeignClient.updateStock(items);

        assertEquals(null, result);
    }
}
