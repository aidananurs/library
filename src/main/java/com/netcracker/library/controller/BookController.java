package com.netcracker.library.controller;

import com.netcracker.library.exceptions.NotFoundException;
import com.netcracker.library.model.BookDto;
import com.netcracker.library.model.SaveBookRequest;
import com.netcracker.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<?> getAllBooks() {
        try {
            var books = bookService.getAll();
            return ResponseEntity.ok(books);
        } catch (Exception e) {
            return createInternalServerErrorStatus(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
        Optional<BookDto> book = bookService.getById(id);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> saveBook(@RequestBody SaveBookRequest request) {
        try {
            bookService.save(request);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return createInternalServerErrorStatus(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody SaveBookRequest request) {
        try {
            bookService.update(id, request);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return createInternalServerErrorStatus(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        try {
            bookService.deleteById(id);
             return ResponseEntity.ok().build();
        } catch (Exception e) {
            return createInternalServerErrorStatus(e.getMessage());
        }
    }

    private ResponseEntity<?> createInternalServerErrorStatus(String message) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + message);
    }

}
