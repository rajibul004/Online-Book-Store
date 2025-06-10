package com.rajibul.book_store.repository;

import com.rajibul.book_store.entity.Books;
import com.rajibul.book_store.entity.CartItems;
import com.rajibul.book_store.entity.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, Long> {
    Optional<CartItems> findByUserAndBook(Users user, Books book);
    List<CartItems> findByUser(Users user);

    List<CartItems> findByUserId(Long userId);
}
