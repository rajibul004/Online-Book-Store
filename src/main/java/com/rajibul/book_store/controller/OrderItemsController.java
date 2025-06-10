package com.rajibul.book_store.controller;

import com.rajibul.book_store.entity.Books;
import com.rajibul.book_store.entity.OrderItems;
import com.rajibul.book_store.entity.Orders;
import com.rajibul.book_store.service.BookService;
import com.rajibul.book_store.service.OrderItemService;
import com.rajibul.book_store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orderitems")
public class OrderItemsController {

    private final OrderItemService orderItemService;
    private final OrderService orderService;
    private final BookService bookService;

    @Autowired
    public OrderItemsController(OrderItemService orderItemService, OrderService orderService, BookService bookService) {
        this.orderItemService = orderItemService;
        this.orderService = orderService;
        this.bookService = bookService;
    }

    // Create a new OrderItem
    @PostMapping("/add")
    public ResponseEntity<OrderItems> addOrderItem(
            @RequestParam int orderId,
            @RequestParam Long bookId,
            @RequestParam int quantity,
            @RequestParam BigDecimal price) {

        // Fetch existing order and book from their respective services
        Orders existingOrder = orderService.findById(orderId);
        Books existingBook = bookService.findById(bookId);

        if (existingOrder == null || existingBook == null) {
            return ResponseEntity.badRequest().build(); // Handle errors as appropriate
        }

        // Create and save the new OrderItem
        OrderItems newOrderItem = new OrderItems(existingOrder, existingBook, quantity, price);
        orderItemService.save(newOrderItem);
        return ResponseEntity.ok(newOrderItem);
    }

    // Get all OrderItems
    @GetMapping("/all")
    public ResponseEntity<List<OrderItems>> getAllOrderItems() {
        List<OrderItems> orderItems = orderItemService.findAll();
        return new ResponseEntity<>(orderItems, HttpStatus.OK);
    }

    // Get a specific OrderItem by ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderItems> getOrderItemById(@PathVariable int id) {
        Optional<OrderItems> orderItem = orderItemService.findById(id);
        return orderItem.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update an existing OrderItem
    @PutMapping("/update/{id}")
    public ResponseEntity<OrderItems> updateOrderItem(@PathVariable int id, @RequestBody OrderItems orderItemDetails) {
        Optional<OrderItems> existingOrderItem = orderItemService.findById(id);

        if (existingOrderItem.isPresent()) {
            OrderItems orderItem = existingOrderItem.get();
            orderItem.setQuantity(orderItemDetails.getQuantity());
            orderItem.setPrice(orderItemDetails.getPrice());
            OrderItems updatedOrderItem = orderItemService.save(orderItem);
            return new ResponseEntity<>(updatedOrderItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete an OrderItem by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable int id) {
        Optional<OrderItems> existingOrderItem = orderItemService.findById(id);

        if (existingOrderItem.isPresent()) {
            orderItemService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
