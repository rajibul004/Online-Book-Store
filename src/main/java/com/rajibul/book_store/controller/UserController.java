package com.rajibul.book_store.controller;
import com.rajibul.book_store.entity.Users;
import com.rajibul.book_store.service.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<Users> findAll(@RequestBody Users user) {
        return userService.findAll();
    }
    @GetMapping("/users/{id}")
    public Object findById(@PathVariable int id) {
        Optional<Users> users = userService.findById(id);
        if (users != null) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/register")
    public Users save(@RequestBody Users users) {
        return userService.save(users);
    }
    //Update existing employee
    @PutMapping("/updateusers")
    public Users updateUser(@RequestBody Users users) {
        return userService.save(users);
    }
    @GetMapping("/login")
    public ResponseEntity<Users> login(@RequestBody Users users) {
        Optional<Users> user = userService.findByUserName(users.getUserName());
        if (user.isPresent() && user.get().getPassword().equals(users.getPassword())) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(401).build();  // Return 401 Unauthorized if login fails
        }
    }

}
