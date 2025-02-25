package com.netcracker.library.controller;

import com.netcracker.library.exceptions.NotFoundException;
import com.netcracker.library.model.AuthorDto;
import com.netcracker.library.model.SaveAuthorRequest;
import com.netcracker.library.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<?> getAllAuthors() {
        try {
            var authors = authorService.getAll();
            return ResponseEntity.ok(authors);
        } catch (Exception e) {
            return createInternalServerErrorStatus(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable Long id) {
        Optional<AuthorDto> author = authorService.getById(id);
        return author.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> saveAuthor(@RequestBody SaveAuthorRequest request) {
        try {
            authorService.save(request);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return createInternalServerErrorStatus(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable Long id, @RequestBody SaveAuthorRequest request) {
        try {
            authorService.update(id, request);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return createInternalServerErrorStatus(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id) {
        try {
            authorService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return createInternalServerErrorStatus(e.getMessage());
        }
    }

    private ResponseEntity<?> createInternalServerErrorStatus(String message) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + message);
    }
}
