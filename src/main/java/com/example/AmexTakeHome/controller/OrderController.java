package com.example.AmexTakeHome.controller;

import com.example.AmexTakeHome.entity.FailedResponse;
import com.example.AmexTakeHome.entity.Item;
import com.example.AmexTakeHome.entity.OrderRequest;
import com.example.AmexTakeHome.entity.OrderSummary;
import com.example.AmexTakeHome.entity.OrderResponse;
import com.example.AmexTakeHome.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Notes:
 * 
 * Added the OrderResponse interface to allow the returning of either a summary or a failure
 * Added the FailedResponse to give feedback on failures
 * Added the null check to the fetching
 * Renamed the endpoints 
 * Changed all responses to Summaries rather than the request
 */
@RequestMapping(value = "/orders")
@RestController
public class OrderController {

    private OrderService orderService;

    public OrderController(@Autowired OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping
    List<OrderSummary> all() {
        return orderService.fetchAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderSummary> displayOrder(@PathVariable("id")  int id) {
        OrderSummary summary = orderService.fetchOrder(id);
        if(summary == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(summary);
        }
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest request) {
        if(!requestValidated(request)){
            return ResponseEntity.badRequest().body(new FailedResponse("Item request is not stocked in this store."));
        }
        try {
            return ResponseEntity.ok(orderService.processOrder(request));
        } catch (Exception e) {
            // ^^ Catch more granular exceptions to return better responses to the consumer
            return ResponseEntity.internalServerError().body(new FailedResponse("Encountered Unknown Error: " + e.getMessage()));
        }
    }

    // Validation could be further enhanced to return a complex object that also contains the causes of failure 
    // Allowing a better error message, i.e. returning the item that isnt contain the Item enum
    private boolean requestValidated(OrderRequest request) {
        
        // Get all the Enums as a List
        List<String> names = Arrays.asList(Item.values())                
            // Stream it so we can use mapping functions
            .stream()
            // Convert the items to their String names
            .map(item -> item.name().toLowerCase())
            // Rejoin into a list
            .collect(Collectors.toList());


        // could add more complex validation logic here, this just confirms that the string given is actually an Item as per the enum
        // `allMatch` ensures that every item in the keyset matches the expression `it -> names.contains(it)`
        return request.getItems().keySet().stream().allMatch( it -> names.contains(it.toLowerCase()));
    }
}
