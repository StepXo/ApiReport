package com.emazon.ApiReport.Domain.Model;

import com.emazon.ApiReport.Domain.Utils.SaleBuilder;

import java.util.List;

public class Sale {
    long id;
    long userId;
    String email;
    List<Item> items;
    double total;
    String date;

    public Sale(SaleBuilder builder) {
        this.id = builder.getId();
        this.userId = builder.getUserId();
        this.email = builder.getEmail();
        this.items = builder.getItems();
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

    public List<Item> getItems() {
        return items;
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

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
