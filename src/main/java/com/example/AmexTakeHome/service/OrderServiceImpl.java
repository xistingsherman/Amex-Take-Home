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
        int discount = 0;
        double quantity= 0;

        for(String key: items.keySet()){
            if (key.equalsIgnoreCase("apple")) {
                quantity = (int) items.get(key).intValue();

                if (quantity % 2 == 0)
                    discount = (int)quantity / 2;
                else
                    discount = (int) Math.ceil(quantity / 2);
                cost += discount * Item.APPLE.cost;
                //fix rounding error
                cost = Math.round(cost * 100.0) / 100.0;
            }
            if (key.equalsIgnoreCase("orange")) {
                quantity = (int) items.get(key).intValue();

                discount = (int)(quantity / 3) * 2;

                if (quantity % 3 == 1)
                    discount += 1;
                else if (quantity % 3 == 2)
                    discount += 2;

                cost += discount * Item.ORANGE.cost;
            }
        }

        summary.put("cost", cost);
        return summary;
    }
}
