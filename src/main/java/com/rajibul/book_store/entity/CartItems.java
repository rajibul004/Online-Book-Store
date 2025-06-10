package com.rajibul.book_store.entity;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "cart_items")
public class CartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Books book;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    // Constructors
    public CartItems() {
    }

    public CartItems(Users user, Books book, int quantity) {
        this.user = user;
        this.book = book;
        this.quantity = quantity;
    }

    // Getters and Setters
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUsers() {
        return user;
    }

    public void setUsers(Users user) {
        this.user= user;
    }

    public Books getBooks() {
        return book;
    }

    public void setBooks(Books book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItems{" +
                "id=" + id +
                ", userId=" + (user != null ? user.getId() : null) +
                ", bookId=" + (book != null ? book.getId() : null) +
                ", quantity=" + quantity +
                '}';
    }
}