package com.example.AmexTakeHome.service;

import com.example.AmexTakeHome.entity.OrderSummary;
import com.example.AmexTakeHome.entity.OrderRequest;

import java.util.List;

public interface OrderService {

    OrderSummary processOrder(OrderRequest request);
    List<OrderSummary> fetchAllOrders();
    OrderSummary fetchOrder(int orderID);
}
