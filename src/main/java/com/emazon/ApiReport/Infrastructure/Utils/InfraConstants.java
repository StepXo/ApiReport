package com.emazon.ApiReport.Infrastructure.Utils;

public class InfraConstants {
    public static final String ZERO = "0 ";
    public static final String TEN = "10 ";
    public static final String CART_REQUEST = "asc";

    public static final int PAGE = 0;
    public static final int SIZE = 10;
    public static final int SEVEN = 7;

    public static final String SPRING = "Spring";
    public static final String BEARER = "Bearer ";
    public static final String AUTHORIZATION = "Authorization";
    public static final String MESSAGE = "Message";
    public static final String ITEM = "item";

    public static final String USER_API = "Api-User";
    public static final String STOCK_API = "Api-Stock";
    public static final String CART_API = "ApiCart";
    public static final String TRANSACTION_API = "ApiTransaction";


    public static final String USER = "/{id}";
    public static final String ORDER = "/{order}";
    public static final String BUY = "/buy";
    public static final String GET_SUPPLY = "/date/";


    public static final String ITEM_NOT_FOUND = "No item was found with that name";
    public static final String QUANTITY_IS_NOT_ENOUGH = "The quantity is not available";
    public static final String INVALID_USER = "The user must not be null or empty";
    public static final String CART_IS_NULL = "The cart cannot be null";
    public static final String CART_IS_EMPTY = "The cart is empty";

    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";

    private InfraConstants() {
        throw new UnsupportedOperationException("This is a constants class and cannot be instantiated.");
    }
}
