package com.emazon.ApiReport.Application.Service;

import com.emazon.ApiReport.Application.Handler.SaleHandler;
import com.emazon.ApiReport.Application.Response.ItemResponse;
import com.emazon.ApiReport.Application.Response.SaleResponse;
import com.emazon.ApiReport.Domain.Api.SaleServicePort;
import com.emazon.ApiReport.Domain.Model.Sale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class SaleServiceTest {

    @Mock
    private SaleServicePort saleServicePort;

    @Mock
    private SaleHandler saleHandler;

    @InjectMocks
    private SaleService saleService;

    private Sale mockSale;

    @BeforeEach
    void setUp() {
        openMocks(this);
        mockSale = new Sale();
        mockSale.setItems(List.of(1L, 2L, 3L));
        mockSale.setQuantity(List.of(10L, 20L, 30L));
        mockSale.setPrice(List.of(100.0, 200.0, 300.0));
    }

    @Test
    void testSaleSuccess() {
        SaleResponse mockSaleResponse = new SaleResponse();
        when(saleServicePort.sale()).thenReturn(mockSale);
        when(saleHandler.toResponse(mockSale)).thenReturn(mockSaleResponse);

        SaleResponse result = saleService.sale();

        assertEquals(mockSaleResponse, result);
        assertEquals(3, result.getItems().size());

        List<ItemResponse> items = result.getItems();
        assertEquals(1L, items.get(0).getId());
        assertEquals(10L, items.get(0).getQuantity());
        assertEquals(100.0, items.get(0).getPrice());

        assertEquals(2L, items.get(1).getId());
        assertEquals(20L, items.get(1).getQuantity());
        assertEquals(200.0, items.get(1).getPrice());

        assertEquals(3L, items.get(2).getId());
        assertEquals(30L, items.get(2).getQuantity());
        assertEquals(300.0, items.get(2).getPrice());
    }

    @Test
    void testSetItemPrivateMethodUsingReflection() throws Exception {
        Method method = SaleService.class.getDeclaredMethod("setItem", List.class, List.class, List.class);
        method.setAccessible(true);

        @SuppressWarnings("unchecked")
        List<ItemResponse> items = (List<ItemResponse>) method.invoke(saleService, List.of(1L, 2L, 3L), List.of(10L, 20L, 30L), List.of(100.0, 200.0, 300.0));

        // Assert
        assertEquals(3, items.size());
        assertEquals(1L, items.get(0).getId());
        assertEquals(10L, items.get(0).getQuantity());
        assertEquals(100.0, items.get(0).getPrice());

        assertEquals(2L, items.get(1).getId());
        assertEquals(20L, items.get(1).getQuantity());
        assertEquals(200.0, items.get(1).getPrice());

        assertEquals(3L, items.get(2).getId());
        assertEquals(30L, items.get(2).getQuantity());
        assertEquals(300.0, items.get(2).getPrice());
    }
}
