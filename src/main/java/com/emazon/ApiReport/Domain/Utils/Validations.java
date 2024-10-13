package com.emazon.ApiReport.Domain.Utils;

import com.emazon.ApiReport.Domain.Exeptions.*;
import com.emazon.ApiReport.Domain.Model.Cart;

import java.util.ArrayList;
import java.util.List;

import static com.emazon.ApiReport.Domain.Utils.DomConstants.ITEM_ERROR;
import static com.emazon.ApiReport.Domain.Utils.DomConstants.QUANTITY_ERROR;

public class Validations {

    private static void validateEmail(String email) {
        if (email ==null || email.isEmpty() || !DomConstants.EMAIL_PATTERN.matcher(email).matches()) {
            throw new EmailFormatException();
        }
    }

    private static void validateUser(String user) {
        if (user == null || user.trim().isEmpty()) {
            throw new InvalidUserException();
        }
    }
    private static void validateCart(Cart cart) {
        if (cart == null) {
            throw new CartIsNullException();
        }

        if (cart.getItem() == null || cart.getItem().isEmpty() ) {
            throw new EmptyCartException();
        }
    }

    private static List<Long> validateStock(Cart cart,List<Integer> stockValidation){
        List<Long> itemsNotFound = new ArrayList<>();
        List<Long> itemsWithNoStock = new ArrayList<>();

        for (int i = 0; i < stockValidation.size()  ; i++) {

            if (stockValidation.get(i) == ITEM_ERROR) {
                itemsNotFound.add(cart.getItem().get(i).getId());
            }
            if (stockValidation.get(i) == QUANTITY_ERROR) {
                itemsWithNoStock.add(cart.getItem().get(i).getId());
            }
        }
        if (!itemsNotFound.isEmpty()) {
            throw new ItemNotFoundException(String.valueOf(itemsNotFound));
        }
        return itemsWithNoStock;
    }

    public static void validate(Cart cart, String email){
        validateEmail(email);
        validateCart(cart);
    }
    public static List<Long> validate(Cart cart,List<Integer> stockValidation){
        validateCart(cart);
        return validateStock(cart, stockValidation);
    }

    public static void validate( String user){
        validateUser(user);
    }

}
