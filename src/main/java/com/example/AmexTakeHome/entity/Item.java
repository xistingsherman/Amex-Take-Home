package com.example.AmexTakeHome.entity;

import java.util.HashMap;
import java.util.Map;

public enum Item {
    APPLE(0.6),
    ORANGE(0.25);
    public final double cost;

    Item(double cost) {
        this.cost = cost;
    }
}

