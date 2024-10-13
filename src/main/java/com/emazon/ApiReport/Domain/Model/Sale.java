package com.emazon.ApiReport.Domain.Model;

import com.emazon.ApiReport.Domain.Utils.SaleBuilder;

import java.util.List;

public class Sale {
    long id;
    long userId;
    String email;
    List<Long> items;
    List<Long> quantity;
    List<Double> price;
    double total;
    String date;

    public Sale(SaleBuilder builder) {
        this.id = builder.getId();
        this.userId = builder.getUserId();
        this.email = builder.getEmail();
        this.items = builder.getItems();
        this.quantity = builder.getQuantity();
        this.price = builder.getPrice();
        this.total = builder.getTotal();
        this.date = builder.getDate();
    }
    public static SaleBuilder builder() {
        return new SaleBuilder();
    }

    public Sale() {
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public List<Long> getItems() {
        return items;
    }

    public List<Long> getQuantity() {
        return quantity;
    }

    public List<Double> getPrice() {
        return price;
    }

    public double getTotal() {
        return total;
    }

    public String getDate() {
        return date;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setItems(List<Long> items) {
        this.items = items;
    }

    public void setQuantity(List<Long> quantity) {
        this.quantity = quantity;
    }

    public void setPrice(List<Double> price) {
        this.price = price;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
