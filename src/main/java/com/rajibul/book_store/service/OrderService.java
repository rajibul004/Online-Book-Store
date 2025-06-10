package com.rajibul.book_store.service;

import com.rajibul.book_store.entity.OrderItems;
import com.rajibul.book_store.entity.Orders;
import com.rajibul.book_store.repository.OrderItemsRepository;
import com.rajibul.book_store.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemsRepository orderItemsRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderItemsRepository orderItemsRepository) {
        this.orderRepository = orderRepository;
           this.orderItemsRepository = orderItemsRepository;
    }

    // Find an order by ID
    public Orders findById(int id) {
        return orderRepository.findById(id).orElse(null);
    }

    // Find all items for a specific order
    public List<OrderItems> findOrderItemsByOrderId(Long orderId) {
        return orderItemsRepository.findByOrderId(Math.toIntExact(orderId));
    }
    public List<Orders> findAll() {
        return orderRepository.findAll();
    }
    public Orders save(Orders order) {
        // Calculate the total price if it’s not already set
        if (order.getTotalPrice() == null) {
            order.setTotalPrice(calculateTotalPrice(order.getId())); // Ensure this order has items associated with it
        }
        return orderRepository.save(order);
    }


    // Calculate the total price of an order based on its items
    public BigDecimal calculateTotalPrice(Long orderId) {
        List<OrderItems> items = findOrderItemsByOrderId(orderId); // This should work if items are OrderItems
        return items.stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
