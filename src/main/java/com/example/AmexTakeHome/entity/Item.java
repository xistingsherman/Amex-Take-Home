package com.example.AmexTakeHome.entity;

import java.math.BigDecimal;

public enum Item {
    APPLE("0.6"),
    ORANGE("0.25");
    public final BigDecimal cost;

    Item(String cost) {
        this.cost = new BigDecimal(cost);
    }
}

