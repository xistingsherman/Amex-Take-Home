package com.example.AmexTakeHome.service;

import com.example.AmexTakeHome.entity.Item;
import com.example.AmexTakeHome.entity.OrderSummary;
import com.example.AmexTakeHome.entity.SimpleOrder;
import com.example.AmexTakeHome.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public final class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;


    @Override
    public OrderSummary saveOrder(HashMap<String, Integer> items) {

        SimpleOrder order = new SimpleOrder(items);

        orderRepository.save(order);

        OrderSummary summary = new OrderSummary();

        summary.setItems(items);
        summary.setId(order.getId());

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

        summary.setCost(cost);
        return summary;
    }

    @Override
    public List<SimpleOrder> fetchAllOrders() {
        return (List<SimpleOrder>) orderRepository.findAll();
    }

    @Override
    public SimpleOrder fetchOrder(int orderID) {
        return orderRepository.findById(orderID);
    }
}
