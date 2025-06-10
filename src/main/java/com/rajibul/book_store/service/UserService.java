package com.rajibul.book_store.service;

import com.rajibul.book_store.entity.Users;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
public interface UserService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    List<Users> findAll();
    Optional<Users> findById(int theid);
     Users save(Users user);
    void deleteById(int theid);


    Optional<Users> findByUserName(String userName);
}