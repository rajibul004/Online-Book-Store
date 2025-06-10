package com.rajibul.book_store.controller;

public class cartItemRequest {
    private Long bookId; // Assuming book ID is of type Long
    private int quantity;
    private Long userId;

    // Getters and setters
    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "cartItemRequest{" +
                "bookId=" + bookId +
                ", quantity=" + quantity +
                ", userId=" + userId +
                '}';
    }
}

