package com.emazon.ApiReport.Domain.UseCase;

import com.emazon.ApiReport.Domain.Exeptions.*;
import com.emazon.ApiReport.Domain.Model.Cart;
import com.emazon.ApiReport.Domain.Model.Item;
import com.emazon.ApiReport.Domain.Model.Sale;
import com.emazon.ApiReport.Domain.Spi.CartFeignPort;
import com.emazon.ApiReport.Domain.Spi.StockFeignPort;
import com.emazon.ApiReport.Domain.Spi.TransactionFeignPort;
import com.emazon.ApiReport.Domain.Spi.UserJwtPort;
import com.emazon.ApiReport.Domain.Spi.SalePersistencePort;
import com.emazon.ApiReport.Domain.Utils.Validations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class SaleUseCaseTest {

    @Mock private SalePersistencePort salePersistencePort;
    @Mock private UserJwtPort userJwt;
    @Mock private StockFeignPort stockFeignPort;
    @Mock private CartFeignPort cartFeignPort;
    @Mock private TransactionFeignPort transactionFeignPort;

    private SaleUseCase saleUseCase;
    private Sale sale;
    private Cart cart;
    private List<Integer> list;
    private String email;
    private String user;
    private List<Item> itemList;

    private long userId;




    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        saleUseCase = new SaleUseCase(salePersistencePort, userJwt, stockFeignPort, cartFeignPort, transactionFeignPort);
        user = "12345";
        userId = Long.parseLong(user);
        email = "test@example.com";
        list = new ArrayList<>();
        cart = new Cart();
        itemList = Arrays.asList(
                setItem(1L, "item1", 2, 100.0),
                setItem(2L, "item2", 3, 150.0));
        cart.setItem(itemList);

        sale = Sale.builder()
                .setUserId(userId)
                .setEmail(email)
                .setItems(Arrays.asList(1L, 2L))
                .setQuantity(Arrays.asList(1L, 2L)).build();
    }

    private Item setItem(long index,String name,long quantity,double price) {
        Item item = new Item();
        item.setId(index);
        item.setName(name);
        item.setQuantity(quantity);
        item.setPrice(price);
        return item;
    }

    @Test
    void shouldSaveSuccessfully() {
        when(userJwt.extractUserId()).thenReturn(user);
        when(userJwt.extractEmail(Long.parseLong(user))).thenReturn(email);
        when(cartFeignPort.getCart()).thenReturn(cart);
        when(stockFeignPort.updateStock(cart.getItem())).thenReturn(list);
        when(salePersistencePort.saveSale(any(Sale.class))).thenReturn(sale);

        Sale saleResponse = saleUseCase.sale();

        assertEquals(userId, saleResponse.getUserId());
        assertEquals(email, saleResponse.getEmail());
    }
    @Test
    void shouldFillItemsListsCorrectly() {
        when(userJwt.extractUserId()).thenReturn(user);
        when(userJwt.extractEmail(Long.parseLong(user))).thenReturn(email);
        when(cartFeignPort.getCart()).thenReturn(cart);
        when(stockFeignPort.updateStock(cart.getItem())).thenReturn(list);
        when(salePersistencePort.saveSale(any(Sale.class))).thenReturn(sale);

        // Ejecutar el método
        List<Long> items = new ArrayList<>();
        List<Long> quantities = new ArrayList<>();
        List<Double> prices = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            items.add(cart.getItem().get(i).getId());
            quantities.add(cart.getItem().get(i).getQuantity());
            prices.add(cart.getItem().get(i).getPrice());
        }

        // Validaciones
        assertEquals(2, items.size());
        assertEquals(Long.valueOf(1L), items.get(0));
        assertEquals(Long.valueOf(2L), items.get(1));
        assertEquals(Long.valueOf(2L), quantities.get(0));
        assertEquals(Long.valueOf(3L), quantities.get(1));
        assertEquals(Double.valueOf(100.0), prices.get(0));
        assertEquals(Double.valueOf(150.0), prices.get(1));
    }

    @Test
    void shouldSetItemCorrectly() {
        when(userJwt.extractUserId()).thenReturn(user);
        when(userJwt.extractEmail(Long.parseLong(user))).thenReturn(email);
        when(cartFeignPort.getCart()).thenReturn(cart);
        when(stockFeignPort.updateStock(cart.getItem())).thenReturn(list);
        when(salePersistencePort.saveSale(any(Sale.class))).thenReturn(sale);

        Sale saleResponse = saleUseCase.sale();

    }

    @Test
    void shouldThrowInvalidUserExceptionWhenUserIsNull() {
        when(userJwt.extractUserId()).thenReturn(null);

        assertThrows(InvalidUserException.class, () -> saleUseCase.sale());
    }

    @Test
    void shouldThrowInvalidEmailExceptionForInvalidEmailValues() {
        String[] invalidEmails = {null, "", "invalid"};

        when(userJwt.extractUserId()).thenReturn(user);
        when(cartFeignPort.getCart()).thenReturn(cart);

        for (String invalid : invalidEmails) {
            when(userJwt.extractEmail(Long.parseLong(user))).thenReturn(invalid);

            assertThrows(EmailFormatException.class, () -> saleUseCase.sale());
        }
    }

    @Test
    void shouldThrowCartIsNullExceptionWhenCartIsNull() {
        when(userJwt.extractUserId()).thenReturn(user);
        when(userJwt.extractEmail(Long.parseLong(user))).thenReturn(email);
        when(cartFeignPort.getCart()).thenReturn(null);

        assertThrows(CartIsNullException.class, () -> saleUseCase.sale());
    }

    @Test
    void shouldThrowEmptyCartExceptionWhenCartIsEmpty() {
        cart.setItem(null);
        when(userJwt.extractUserId()).thenReturn(user);
        when(userJwt.extractEmail(Long.parseLong(user))).thenReturn(email);
        when(cartFeignPort.getCart()).thenReturn(cart);

        assertThrows(EmptyCartException.class, () -> saleUseCase.sale());
    }
    @Test
    void shouldThrowItemNotFoundExceptionItemsAreInvalid() {
        list.add(1);
        when(userJwt.extractUserId()).thenReturn(user);
        when(userJwt.extractEmail(Long.parseLong(user))).thenReturn(email);
        when(cartFeignPort.getCart()).thenReturn(cart);
        when(stockFeignPort.updateStock(cart.getItem())).thenReturn(list);


        assertThrows(ItemNotFoundException.class, () -> saleUseCase.sale());
    }
    @Test
    void shouldThrowQuantityIsNotEnoughExceptionWhenStockIsInsufficient() {
        list.add(2);
        when(userJwt.extractUserId()).thenReturn(user);
        when(userJwt.extractEmail(Long.parseLong(user))).thenReturn(email);
        when(cartFeignPort.getCart()).thenReturn(cart);
        when(stockFeignPort.updateStock(cart.getItem())).thenReturn(list);

        List<Long> stockList = List.of(2L);
        when(transactionFeignPort.getErrorMessage(stockList)).thenReturn("Not enough stock");

        assertThrows(QuantityIsNotEnough.class, () -> saleUseCase.sale());
    }
}

