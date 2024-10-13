package com.emazon.ApiReport.Domain.Model;

import java.util.List;

public class Cart {
    private long id;
    private long userId;
    private List<Item> item;
    private double total;
    private String actualizationDate;
    private String creationDate;

    public Cart(long id, long userId, List<Item> item, double total, String actualizationDate, String creationDate) {
        this.id = id;
        this.userId = userId;
        this.item = item;
        this.total = total;
        this.actualizationDate = actualizationDate;
        this.creationDate = creationDate;
    }

    public Cart() {
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public List<Item> getItem() {
        return item;
    }

    public double getTotal() {
        return total;
    }

    public String getActualizationDate() {
        return actualizationDate;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setActualizationDate(String actualizationDate) {
        this.actualizationDate = actualizationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}
