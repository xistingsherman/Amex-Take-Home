package com.example.AmexTakeHome;

import com.example.AmexTakeHome.entity.SimpleOrder;
import com.example.AmexTakeHome.repository.OrderRepository;
import com.example.AmexTakeHome.service.OrderServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTests {

    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    public void addOrderService(){
        HashMap<String, Integer> sampleData = new HashMap<>(Map.of("apple", 1, "orange", 2));
        orderService.saveOrder(sampleData);
        assertEquals(orderService.fetchOrder(1).getItems().toString(), sampleData.toString());
    }

    @Test
    public void fetchAllOrderService(){
        HashMap<String, Integer> sampleData = new HashMap<>(Map.of("apple", 1, "orange", 2));
        orderService.saveOrder(sampleData);

        List<SimpleOrder> list = new ArrayList<>();
        list.add(new SimpleOrder(sampleData));

        assertEquals(orderService.fetchAllOrders().toString(), list.toString());
    }

    @Test
    public void fetchByIdOrderService(){
        HashMap<String, Integer> sampleData = new HashMap<>(Map.of("apple", 1, "orange", 2));
        orderService.saveOrder(sampleData);

        assertEquals(orderService.fetchOrder(1).getItems().toString(), sampleData.toString());
    }


}


