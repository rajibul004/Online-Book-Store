package com.rajibul.book_store.controller;

import com.rajibul.book_store.entity.OrderItems;
import com.rajibul.book_store.entity.Orders;
import com.rajibul.book_store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {

    private final OrderService orderService;

    @Autowired
    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Orders> getAllOrders() {
        return orderService.findAll();
    }

    @GetMapping("/{orderId}/items")
    public List<OrderItems> showOrderItems(@PathVariable Long orderId) {
        return orderService.findOrderItemsByOrderId(orderId);
    }

    @PostMapping("/placeOrder")
    public Orders placeOrder(@RequestBody Orders order) {
        return orderService.save(order);
    }
}

