package com.example.AmexTakeHome.entity;


import java.util.HashMap;

public class SimpleOrder {
    private int id;
    private HashMap<String, Integer> items;
    public static int count = 0;

    public SimpleOrder(HashMap<String, Integer> items) {
        count++;
        this.id = count;
        this.items = items;
    }

    public SimpleOrder() {

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

    public void setId(int id) {
        this.id = id;
    }
}
