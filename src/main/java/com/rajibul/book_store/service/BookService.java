package com.rajibul.book_store.service;

import com.rajibul.book_store.entity.Books;

import java.util.List;

public  interface BookService {
    List<Books> findAll();
   Books findById(Long id);
   Books save(Books books);
   void deleteById(Long id);
}
