package com.example.AmexTakeHome;

import com.example.AmexTakeHome.service.OrderService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTests {

    @InjectMocks
    private OrderService orderService;

    @Test
    public void testOrderService() {
        HashMap<String, Integer> testData = new HashMap<>();
        testData.put("apple", 5);
        testData.put("orange", 3);

        HashMap<Object, Object> result = new HashMap<>();
        result.put("items", testData);
        result.put("cost", 3);
        double cost = 3;

        when(this.orderService.createOrder(testData)).thenReturn(result);
        assertEquals(this.orderService.createOrder(testData).get("cost"), cost);
    }


}
