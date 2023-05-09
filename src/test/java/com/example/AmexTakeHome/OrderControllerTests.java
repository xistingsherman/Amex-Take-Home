package com.example.AmexTakeHome;

import com.example.AmexTakeHome.controller.OrderController;
import com.example.AmexTakeHome.entity.FailedResponse;
import com.example.AmexTakeHome.entity.OrderRequest;
import com.example.AmexTakeHome.entity.OrderResponse;
import com.example.AmexTakeHome.entity.OrderSummary;
import com.example.AmexTakeHome.service.OrderServiceImpl;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderControllerTests {

    private OrderServiceImpl orderService = mock(OrderServiceImpl.class);
    private OrderController orderController = new OrderController(orderService);

    @Test
    public void goodRequest(){        
        OrderRequest request = new OrderRequest(new HashMap<>(Map.of("apple", 1, "orange", 2)));

        when(orderService.processOrder(any()))
            .thenReturn(new OrderSummary(new HashMap<>(Map.of()), new BigDecimal("1.0"), 0));

        ResponseEntity<OrderResponse> response = orderController.createOrder(request);

        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void weDontHaveBananas(){        
        OrderRequest request = new OrderRequest(new HashMap<>(Map.of("apple", 1, "banana", 2)));

        ResponseEntity<OrderResponse> response = orderController.createOrder(request);

        assertEquals(400, response.getStatusCode().value());
        assertEquals(FailedResponse.class, response.getBody().getClass());
        assertEquals("Item request is not stocked in this store.", ((FailedResponse)response.getBody()).getMessage());
    }

    @Test
    public void returnServerErrors(){        
        OrderRequest request = new OrderRequest(new HashMap<>(Map.of("apple", 1, "orange", 2)));

        when(orderService.processOrder(any())).thenThrow(new RuntimeException("Unit Test Exception"));

        ResponseEntity<OrderResponse> response = orderController.createOrder(request);

        assertEquals(500, response.getStatusCode().value());
        assertEquals(FailedResponse.class, response.getBody().getClass());
        assertEquals("Encountered Unknown Error: Unit Test Exception", ((FailedResponse)response.getBody()).getMessage());
    }
}

