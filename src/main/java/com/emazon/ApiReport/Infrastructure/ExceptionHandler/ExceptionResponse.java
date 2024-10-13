package com.emazon.ApiReport.Infrastructure.ExceptionHandler;


public enum ExceptionResponse {
    ITEM_NOT_FOUND("The following items were not found in stock: "),
    INVALID_ITEM("Item ID is invalid"),
    INVALID_USER("The user must not be null or empty"),
    CART_IS_NULL("The cart cannot be null"),
    EMPTY_CART("The cart is empty")
    ;


    private String message;

    ExceptionResponse(String message) {
            this.message = message;
        }
        public String getMessage() {
            return this.message;
        }

}
