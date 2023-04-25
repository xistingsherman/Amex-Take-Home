package com.example.AmexTakeHome.service;

import com.example.AmexTakeHome.entity.OrderSummary;
import com.example.AmexTakeHome.entity.SimpleOrder;

import java.util.HashMap;
import java.util.List;

public interface OrderService {

    OrderSummary saveOrder(HashMap<String, Integer> items);
    List<SimpleOrder> fetchAllOrders();
    SimpleOrder fetchOrder(int orderID);
}
