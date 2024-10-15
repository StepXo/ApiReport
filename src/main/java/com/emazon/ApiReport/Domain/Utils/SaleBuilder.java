package com.emazon.ApiReport.Domain.Utils;

import com.emazon.ApiReport.Domain.Model.Item;
import com.emazon.ApiReport.Domain.Model.Sale;

import java.util.List;

public class SaleBuilder {
    long id;
    long userId;
    String email;
    List<Item> items;
    double total;
    String date;

    public SaleBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public SaleBuilder setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public SaleBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public SaleBuilder setItems(List<Item> items) {
        this.items = items;
        return this;
    }

    public SaleBuilder setTotal(double total) {
        this.total = total;
        return this;
    }

    public SaleBuilder setDate(String date) {
        this.date = date;
        return this;
    }

    public Sale build() {
        return new Sale(this);
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
}
