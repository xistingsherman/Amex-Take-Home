package com.example.AmexTakeHome.entity;


import java.util.HashMap;

public class OrderSummary {
    private HashMap<String, Integer> items;
    private double cost;

    private int id;

    public OrderSummary() {
    }

    public HashMap<String, Integer> getItems() {
        return items;
    }

    public void setItems(HashMap<String, Integer> items) {
        this.items = items;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
