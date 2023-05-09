package com.example.AmexTakeHome.entity;


import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class OrderRequest {
    private HashMap<String, Integer> items;
    @JsonIgnore
    public static int count = 0;
    @JsonIgnore
    private int id = count++;

    public OrderRequest(HashMap<String, Integer> items) {
        this.items = items;
    }

    public OrderRequest() {

    }

    public HashMap<String, Integer> getItems() {
        return items;
    }

    public void setItems(HashMap<String, Integer> items) {
        this.items = items;
    }

    public static int getCount() {
        return count;
    }

    public int getId() {
        return id;
    }
}
