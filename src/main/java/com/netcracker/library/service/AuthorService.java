package com.netcracker.library.service;

import com.netcracker.library.model.AuthorDto;
import com.netcracker.library.model.SaveAuthorRequest;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    void save(SaveAuthorRequest request);
    void update(Long id, SaveAuthorRequest request);
    Optional<AuthorDto> getById(Long authorId);
    List<AuthorDto> getAll();
    void deleteById(Long authorId);
}
