package com.example.AmexTakeHome.entity;


import java.math.BigDecimal;
import java.util.HashMap;

public class OrderSummary implements OrderResponse {
    private HashMap<String, Integer> items;
    private BigDecimal cost;

    private int id;

    public OrderSummary(OrderRequest request, BigDecimal cost) {
        this.items = request.getItems();
        this.cost = cost;
        this.id = request.getId();
    }
    
    public OrderSummary(HashMap<String, Integer> items, BigDecimal cost, int id) {
        this.items = items;
        this.cost = cost;
        this.id = id;
    }
    public HashMap<String, Integer> getItems() {
        return items;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public int getId() {
        return id;
    }
    
}
