package com.example.AmexTakeHome;

import com.example.AmexTakeHome.entity.SimpleOrder;
import com.example.AmexTakeHome.repository.OrderRepository;
import com.example.AmexTakeHome.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class OrderRepositoryTests {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderService orderService;

    @Test
    public void addOrderToRepository(){
        SimpleOrder order = new SimpleOrder();
        HashMap<String, Integer> sampleData = new HashMap<>(Map.of("apple", 1, "orange", 2));
        order.setItems(sampleData );
        orderRepository.save(order);

        assertEquals(orderRepository.findById(order.getId()),order);
    }

    @Test
    public void getOrderById(){
        SimpleOrder order = new SimpleOrder();
        HashMap<String, Integer> sampleData = new HashMap<>(Map.of("apple", 1, "orange", 2));
        order.setItems(sampleData);
        orderRepository.save(order);

        assertEquals(orderRepository.findById(1),order);
    }

    @Test
    public void getAllOrders(){
        SimpleOrder order = new SimpleOrder();
        HashMap<String, Integer> sampleData = new HashMap<>(Map.of("apple", 1, "orange", 2));
        order.setItems(sampleData);

        SimpleOrder order2 = new SimpleOrder();
        HashMap<String, Integer> sampleData2 = new HashMap<>(Map.of("apple", 1, "orange", 2));
        order.setItems(sampleData2);

        List<SimpleOrder> list = new ArrayList<>();
        list.add(order);
        list.add(order2);

        orderRepository.save(order);

        assertEquals(orderRepository.findAll(),list);
    }
}
