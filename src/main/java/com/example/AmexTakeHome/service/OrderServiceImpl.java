package com.example.AmexTakeHome.service;

import com.example.AmexTakeHome.entity.Item;
import com.example.AmexTakeHome.entity.OrderSummary;
import com.example.AmexTakeHome.entity.OrderRequest;
import com.example.AmexTakeHome.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    public OrderServiceImpl(@Autowired OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderSummary processOrder(OrderRequest request) {

        OrderSummary summary = new OrderSummary(
            request,
            calculateCost(request)
        );

        orderRepository.save(summary);
        return summary;
    }

    private BigDecimal calculateCost(OrderRequest request) {
        BigDecimal cost = new BigDecimal("0.0");
        int effectiveQuantity;
        double quantity;

        for(String key: request.getItems().keySet()){
            if (key.equalsIgnoreCase("apple")) {
                quantity = request.getItems().get(key);

                if (quantity % 2 == 0)
                    effectiveQuantity = (int)quantity / 2;
                else
                    effectiveQuantity = (int) Math.ceil(quantity / 2);
                cost = cost.add(Item.APPLE.cost.multiply(new BigDecimal(effectiveQuantity)));
            }
            if (key.equalsIgnoreCase("orange")) {
                quantity = request.getItems().get(key).intValue();

                effectiveQuantity = (int)(quantity / 3) * 2;

                if (quantity % 3 == 1)
                    effectiveQuantity += 1;
                else if (quantity % 3 == 2)
                    effectiveQuantity += 2;

                cost = cost.add(Item.ORANGE.cost.multiply(new BigDecimal(effectiveQuantity)));
            }
        }
        return cost;
    }

    @Override
    public List<OrderSummary> fetchAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public OrderSummary fetchOrder(int orderID) {
        return orderRepository.findById(orderID);
    }
}
