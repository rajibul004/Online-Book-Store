package com.rajibul.book_store.service;

import com.rajibul.book_store.entity.Books;
import com.rajibul.book_store.entity.OrderItems;
import com.rajibul.book_store.entity.Orders;
import com.rajibul.book_store.repository.OrderItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {
    private final OrderItemsRepository orderItemsRepository;
    @Autowired
    public OrderItemService(OrderItemsRepository orderItemsRepository) {
        this.orderItemsRepository = orderItemsRepository;
    }

    public static OrderItems createOrderItem(Orders existingOrder, Books existingBook, int quantity, BigDecimal price) {
        return new OrderItems(existingOrder, existingBook, quantity, price);
    }


    public List<OrderItems> findAll() {
        return orderItemsRepository.findAll();
    }

    public Optional<OrderItems> findById(int id) {
        return orderItemsRepository.findById(id);
    }

    public OrderItems save(OrderItems orderItems) {
        return orderItemsRepository.save(orderItems);
    }


    public void deleteById(int id) {
        orderItemsRepository.deleteById(id);
    }
}
