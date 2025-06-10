package com.rajibul.book_store.service;

import com.rajibul.book_store.entity.CartItems;

import java.util.List;

public interface CartItemsServiceImpl {
    List<CartItems> getCartItemsByUserId(Long userId);

}
