package com.example.AmexTakeHome.controller;

import com.example.AmexTakeHome.entity.OrderSummary;
import com.example.AmexTakeHome.entity.SimpleOrder;
import com.example.AmexTakeHome.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;

@RequestMapping(value = "/shop")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    List<SimpleOrder> all() {
        return orderService.fetchAllOrders();
    }

    @GetMapping("/orders/{id}")
    SimpleOrder displayOrder(@PathVariable("id")  int id) {
        return orderService.fetchOrder(id);
    }

    @PostMapping("/create")
    public OrderSummary createOrder(@RequestBody HashMap<String, Integer> items) {
        OrderSummary summary = orderService.saveOrder(items);
        return summary;
    }

}
