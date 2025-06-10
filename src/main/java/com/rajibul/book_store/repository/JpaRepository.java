package com.rajibul.book_store.repository;
import com.rajibul.book_store.entity.OrderItems;
import com.rajibul.book_store.entity.Users;

import java.util.List;

public interface JpaRepository<O, L extends Number> {
    List<OrderItems> findAll();
    Users findById(Long theid);
    OrderItems save(OrderItems theUsers);
    void deleteById(Long theid);

}
