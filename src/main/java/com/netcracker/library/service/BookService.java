package com.netcracker.library.service;

import com.netcracker.library.exceptions.NotFoundException;
import com.netcracker.library.model.BookDto;
import com.netcracker.library.model.SaveBookRequest;

import java.util.List;
import java.util.Optional;

public interface BookService {
    void save(SaveBookRequest request) throws NotFoundException;
    void update(Long id, SaveBookRequest request);
    Optional<BookDto> getById(Long bookId);
    List<BookDto> getAll();
    void deleteById(Long bookId);
}
