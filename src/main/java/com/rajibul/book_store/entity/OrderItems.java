package com.rajibul.book_store.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Order must not be null")
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false) // Foreign key to the orders table
    private Orders order;  // Changed to Orders type

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Books book;

    @NotNull
    @Min(value = 1, message = "Quantity must be at least 1") // Validates quantity
    private int quantity;

    @NotNull
    @Positive
    private BigDecimal price;

    // Default constructor
    public OrderItems() {}

    // Parameterized constructor
    public OrderItems(Orders order, Books book, int quantity, BigDecimal price ) {
        this.order = order;
        this.book = book;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Orders getOrder() { // Changed to camel case
        return order;
    }

    public void setOrder(Orders order) { // Changed to camel case
        this.order = order;
    }
    public Books getBook() {
        return book;
    }
    public void setBook(Books book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderItems{" +
                "id=" + id +
                ", order=" + order + // Changed to avoid single quotes
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

}
