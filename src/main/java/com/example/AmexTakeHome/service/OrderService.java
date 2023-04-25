package com.example.AmexTakeHome.service;

import java.util.HashMap;

public interface OrderService {

    HashMap<Object, Object> createOrder(HashMap<String, Integer> items);
}
