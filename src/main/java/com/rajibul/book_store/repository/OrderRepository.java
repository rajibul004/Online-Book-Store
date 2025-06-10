package com.rajibul.book_store.repository;

import com.rajibul.book_store.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {
    Optional<Orders> findById(int id);
    List<Orders> findByUserIdAndStatus(Long user_id, String status);
    List<Orders> findByStatus(String status);
}
