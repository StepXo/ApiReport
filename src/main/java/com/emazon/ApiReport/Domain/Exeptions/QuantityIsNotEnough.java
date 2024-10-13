package com.emazon.ApiReport.Domain.Exeptions;

public class QuantityIsNotEnough extends RuntimeException{
    public QuantityIsNotEnough(String message) {
        super(message);
    }

}
