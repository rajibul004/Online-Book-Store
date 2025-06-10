package com.rajibul.book_store.repository;

import com.rajibul.book_store.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems, Integer> {
    List<OrderItems> findByOrderId(int orderId);

}
