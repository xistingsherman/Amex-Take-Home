package com.example.AmexTakeHome;

import com.example.AmexTakeHome.entity.OrderSummary;
import com.example.AmexTakeHome.repository.OrderRepository;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;


public class OrderRepositoryTests {

    OrderRepository orderRepository = new OrderRepository();

    @Test
    public void addOrderToRepository(){
        HashMap<String, Integer> sampleData = new HashMap<>(Map.of("apple", 1, "orange", 2));
        OrderSummary order = new OrderSummary(sampleData, new BigDecimal("0.0"), 1);
        orderRepository.save(order);

        assertEquals(orderRepository.findById(order.getId()), order);
    }

    // Commented this as it does the same as the above test
    @Test
    public void getOrderById(){
        // OrderRequest order = new OrderRequest();
        // HashMap<String, Integer> sampleData = new HashMap<>(Map.of("apple", 1, "orange", 2));
        // order.setItems(sampleData);
        // orderRepository.save(order);

        // assertEquals(orderRepository.findById(1),order);
    }

    @Test
    public void getAllOrders(){
        HashMap<String, Integer> sampleData = new HashMap<>(Map.of("apple", 1, "orange", 2));
        OrderSummary order = new OrderSummary(sampleData, new BigDecimal("0.0"), 1);
        orderRepository.save(order);

        OrderSummary order2 = new OrderSummary(sampleData, new BigDecimal("0.0"), 1);
        orderRepository.save(order2);

        List<OrderSummary> list = new ArrayList<>();
        list.add(order);
        list.add(order2);

        assertEquals(orderRepository.findAll(),list);
    }
}
