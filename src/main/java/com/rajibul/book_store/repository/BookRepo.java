package com.rajibul.book_store.repository;

import com.rajibul.book_store.entity.Books; // Your Books entity
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<Books, Integer> {
    Books findById(Long id);
    // You can define custom query methods here if needed
}
