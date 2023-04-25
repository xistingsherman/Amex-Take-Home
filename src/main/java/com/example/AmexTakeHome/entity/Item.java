package com.example.AmexTakeHome.entity;

public enum Item {
    APPLE(0.6),
    ORANGE(0.25);
    public final double cost;

    Item(double cost) {
        this.cost = cost;
    }
}

