package com.example.AmexTakeHome;

import com.example.AmexTakeHome.entity.OrderRequest;
import com.example.AmexTakeHome.entity.OrderSummary;
import com.example.AmexTakeHome.repository.OrderRepository;
import com.example.AmexTakeHome.service.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderServiceTests {

    private OrderRepository orderRepository = mock(OrderRepository.class);
    private OrderServiceImpl orderService = new OrderServiceImpl(orderRepository);

    @Test
    public void goodOrderProcessed(){        
        OrderRequest request = new OrderRequest(new HashMap<>(Map.of("apple", 1, "orange", 2)));
        OrderSummary result = orderService.processOrder(request);
        assertEquals(result.getItems(), new HashMap<>(Map.of("apple", 1, "orange", 2)));
        assertEquals(result.getCost(), new BigDecimal("1.10"));
    }

    @Test
    public void offersAreApplied(){        
        OrderRequest request = new OrderRequest(new HashMap<>(Map.of("apple", 2, "orange", 3)));
        OrderSummary result = orderService.processOrder(request);
        assertEquals(result.getItems(), new HashMap<>(Map.of("apple", 2, "orange", 3)));
        assertEquals(result.getCost(), new BigDecimal("1.10"));
    }

    // This test is just testing a wrapper function, argument to be made if we even need this
    @Test
    public void fetchAllOrderService(){

        // Mock the order repository response
        when(orderRepository.findAll()).thenReturn(new ArrayList<OrderSummary>(){{
            add(new OrderSummary(new HashMap<>(), new BigDecimal("10.0"), 1));
        }});

        assertEquals(orderService.fetchAllOrders().get(0).getCost(), new BigDecimal("10.0"));
        assertEquals(orderService.fetchAllOrders().get(0).getItems(), new HashMap<>());
        assertEquals(orderService.fetchAllOrders().get(0).getId(), 1);
    }

    // This test is just testing a wrapper function, argument to be made if we even need this
    @Test
    public void fetchByIdOrderService(){
        when(orderRepository.findById(1)).thenReturn(new OrderSummary(new HashMap<>(), new BigDecimal("10.0"), 1));

        assertEquals(orderService.fetchOrder(1).getCost(), new BigDecimal("10.0"));
        assertEquals(orderService.fetchOrder(1).getItems(), new HashMap<>());
        assertEquals(orderService.fetchOrder(1).getId(), 1);
    }


}


