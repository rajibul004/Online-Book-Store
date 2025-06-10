package com.rajibul.book_store.service;

import com.rajibul.book_store.entity.Books;
import com.rajibul.book_store.entity.CartItems;
import com.rajibul.book_store.entity.Users;
import com.rajibul.book_store.repository.BookRepo;
import com.rajibul.book_store.repository.CartItemsRepository;
import com.rajibul.book_store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemsService {

    private final CartItemsRepository cartItemsRepository;
    private final BookRepo bookRepo; // Assuming you have a Books repository
    private final UserRepository userRepository; // Assuming you have a Users repository

    @Autowired
    public CartItemsService(CartItemsRepository cartItemsRepository, BookRepo bookRepo, UserRepository userRepository) {
        this.cartItemsRepository = cartItemsRepository;
        this.bookRepo = bookRepo;
        this.userRepository = userRepository;
    }

    public void save(Long bookId, int quantity, Long userId) {
        // Fetch the book by its ID
        Optional<Books> bookOptional = bookRepo.findById(Math.toIntExact(bookId));
        // Fetch the user by its ID
        Optional<Users> userOptional = userRepository.findById(Math.toIntExact(userId));

        // Check if the book and user exist
        if (bookOptional.isPresent() && userOptional.isPresent()) {
            Books book = bookOptional.get();
            Users user = userOptional.get();

            // Add the item to the cart
            CartItems cartItem = new CartItems(user, book, quantity);
            cartItemsRepository.save(cartItem);
        } else {
            throw new IllegalArgumentException("Invalid book ID or user ID");
        }
    }

    public CartItems addToCart(Users users, Books books, int quantity) {
        CartItems cartItems = cartItemsRepository.findByUserAndBook(users, books)
                .orElse(new CartItems(users, books, quantity)); // Assuming CartItems constructor only takes user, book, and quantity
        cartItems.setQuantity(cartItems.getQuantity() + quantity);
        return cartItemsRepository.save(cartItems);
    }
}
