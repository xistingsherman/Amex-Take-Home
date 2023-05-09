package com.example.AmexTakeHome.repository;

import com.example.AmexTakeHome.entity.OrderSummary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Notes:
 * Changed to save OrderSummary rather than the Request
 */
@Repository
public class OrderRepository {
    List<OrderSummary> orders = new ArrayList<OrderSummary>();

    public List<OrderSummary>  findAll() {
        return orders;
    }

    public void save(OrderSummary order){
        orders.add(order);
    }

    public OrderSummary findById(int id){
        for(OrderSummary order: orders){
            if(order.getId() == id)
                return order;
        }
        return null;
    }

}
