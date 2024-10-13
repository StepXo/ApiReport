package com.emazon.ApiReport.Infrastructure.ExceptionHandler;

import com.emazon.ApiReport.Domain.Exeptions.*;
import com.emazon.ApiReport.Infrastructure.Utils.InfraConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleItemNotFoundException(
            ItemNotFoundException itemNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(InfraConstants.MESSAGE, ExceptionResponse.ITEM_NOT_FOUND.getMessage()));
    }

    @ExceptionHandler(InvalidItemException.class)
    public ResponseEntity<Map<String, String>> invalidItemException(
            InvalidItemException invalidItemException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(InfraConstants.MESSAGE, ExceptionResponse.INVALID_ITEM.getMessage()));
    }
    @ExceptionHandler(InvalidQuantityException.class)
    public ResponseEntity<Map<String, String>> invalidQuantityException(
            InvalidQuantityException invalidQuantityException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(InfraConstants.MESSAGE, ExceptionResponse.INVALID_QUANTITY.getMessage()));
    }
    @ExceptionHandler(InvalidUserException.class)
    public ResponseEntity<Map<String, String>> invalidUserException(
            InvalidUserException invalidUserException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(InfraConstants.MESSAGE, ExceptionResponse.INVALID_USER.getMessage()));
    }
    @ExceptionHandler(CartIsNullException.class)
    public ResponseEntity<Map<String, String>> cartIsNullExeption(
            CartIsNullException cartIsNullException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(InfraConstants.MESSAGE, ExceptionResponse.CART_IS_NULL.getMessage()));
    }
    @ExceptionHandler(EmptyCartException.class)
    public ResponseEntity<Map<String, String>> emptyCartException(
            EmptyCartException emptyCartException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(InfraConstants.MESSAGE, ExceptionResponse.EMPTY_CART.getMessage()));
    }
    @ExceptionHandler(QuantityIsNotEnough.class)
    public ResponseEntity<Map<String, String>> quantityIsNotEnough(
            QuantityIsNotEnough quantityIsNotEnough) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(InfraConstants.MESSAGE, quantityIsNotEnough.getMessage()));
    }

}

