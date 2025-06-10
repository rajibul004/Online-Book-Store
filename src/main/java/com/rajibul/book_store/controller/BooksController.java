package com.rajibul.book_store.controller;

import com.rajibul.book_store.entity.Books;
import com.rajibul.book_store.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/book")
public class BooksController {

    private  final BookService bookService;
    @Autowired
    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/show")
    public List<Books>findAll() {
        return bookService.findAll();
    }
    @GetMapping("/show/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
       Books books = bookService.findById(id);
        if (books==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null); // or you could throw a custom exception
        }
        return ResponseEntity.ok(books);
    }
    @PostMapping("/addbook")
    public Books addBook(@RequestBody Books books) {
        return bookService.save(books);
    }
    @PutMapping("/updatebook")
    public Books updateBook(@RequestBody Books books) {
        return bookService.save(books);
    }
    @DeleteMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable Long id) {
        Books book1=bookService.findById(id) ;
        if (book1==null){
            throw new RuntimeException("Book id not found"+ book1);
        }
        bookService.deleteById(id);
        return "Deleted Book id" + id;
    }

}
