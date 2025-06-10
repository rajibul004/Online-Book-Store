package com.rajibul.book_store.controller;

import com.rajibul.book_store.entity.CartItems;
import com.rajibul.book_store.repository.CartItemsRepository;
import com.rajibul.book_store.service.CartItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartItemsRepository cartItemsRepository;
    private final CartItemsService cartItemsService;
    @Autowired
    public CartController(CartItemsRepository cartItemsRepository , CartItemsService cartItemsService) {
        this.cartItemsRepository = cartItemsRepository;
        this.cartItemsService = cartItemsService;
    }

   @GetMapping("/showAll")
    public List<CartItems> showAll(){
        return cartItemsRepository.findAll();
   }

    @PostMapping("/addToCart")
    public ResponseEntity<?> addToCart(@RequestBody cartItemRequest request) {
        if (request.getBookId() == null) {
            return ResponseEntity.badRequest().body("Book ID cannot be null");
        }
        // Proceed to add the item to the cart
        cartItemsService.save(request.getBookId(), request.getQuantity(), request.getUserId());
        return ResponseEntity.ok("Item added to cart");
    }


}
