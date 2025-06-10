package com.rajibul.book_store.service;

import com.rajibul.book_store.entity.Books;
import com.rajibul.book_store.repository.BookRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookserviceImpl implements BookService {

private final BookRepo bookRepo;


    public BookserviceImpl(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public List<Books> findAll() {
        return bookRepo.findAll();
    }

    @Override
    public Books findById(Long id) {
        return bookRepo.findById(id);
    }

    @Override
    public Books save(Books books) {
        return bookRepo.save(books);
    }

    @Override
    public void deleteById(Long id) {
    bookRepo.deleteById(Math.toIntExact(id));
    }
}
