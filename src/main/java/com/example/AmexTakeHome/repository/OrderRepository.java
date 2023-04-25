package com.example.AmexTakeHome.repository;

import com.example.AmexTakeHome.entity.SimpleOrder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {
    List<SimpleOrder> orders = new ArrayList<SimpleOrder>();

    public List<SimpleOrder>  findAll() {
        return orders;
    }

    public void save(SimpleOrder order){
        orders.add(order);
    }

    public SimpleOrder findById(int id){
        for(SimpleOrder order: orders){
            if(order.getId() == id)
                return order;
        }
        return null;
    }

}
