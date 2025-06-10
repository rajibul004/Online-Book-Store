package com.rajibul.book_store.repository;

import com.rajibul.book_store.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    Optional<Users> findByUserName(String userName);

    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE users AUTO_INCREMENT = (SELECT MAX(id) + 1 FROM users)", nativeQuery = true)
    void resetAutoIncrement();

}
