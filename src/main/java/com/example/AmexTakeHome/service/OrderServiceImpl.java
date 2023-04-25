package com.example.AmexTakeHome.service;

import com.example.AmexTakeHome.entity.Item;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public final class OrderServiceImpl implements OrderService {

    @Override
    public HashMap<Object, Object> createOrder(HashMap<String, Integer> items) {
        HashMap<Object, Object> summary = new HashMap<>();
        summary.put("items", items);

        double cost = 0;

        for(String key: items.keySet()){
            if (key.equalsIgnoreCase("apple"))
                cost += (Integer)items.get(key).intValue() * Item.APPLE.cost;
            if (key.equalsIgnoreCase("orange"))
                cost += (Integer)items.get(key).intValue() * Item.ORANGE.cost;
        }

        summary.put("cost", cost);
        return summary;
    }
}
