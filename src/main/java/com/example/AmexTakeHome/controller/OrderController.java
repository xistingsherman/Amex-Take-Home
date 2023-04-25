package com.example.AmexTakeHome.controller;

import com.example.AmexTakeHome.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public HashMap<Object, Object> createOrder(@RequestBody HashMap<String, Integer> items) {
        HashMap<Object, Object> summary = orderService.createOrder(items);
        return summary;
    }

}
