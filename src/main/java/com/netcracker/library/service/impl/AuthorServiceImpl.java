package com.netcracker.library.service.impl;

import com.netcracker.library.entity.Author;
import com.netcracker.library.exceptions.NotFoundException;
import com.netcracker.library.mapper.AuthorMapper;
import com.netcracker.library.model.AuthorDto;
import com.netcracker.library.model.SaveAuthorRequest;
import com.netcracker.library.repository.AuthorRepository;
import com.netcracker.library.service.AuthorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Override
    public void save(SaveAuthorRequest request) {
        var author = authorMapper.toEntity(request);
        authorRepository.save(author);
    }

    @Override
    @Transactional
    public void update(Long id, SaveAuthorRequest request) {
        Author existingAuthor = authorRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Author with id " + id + " not found"));

        existingAuthor.setName(request.name());
        existingAuthor.setNationality(request.nationality());
        existingAuthor.setBirthDate(request.birthDate());

        authorRepository.save(existingAuthor);
    }

    @Override
    public Optional<AuthorDto> getById(Long authorId) {
        return authorRepository.findById(authorId).map(authorMapper::toDto);
    }

    @Override
    public List<AuthorDto> getAll() {
        return authorRepository.findAll().stream().map(authorMapper::toDto).toList();
    }

    @Override
    @Transactional
    public void deleteById(Long authorId) {
        authorRepository.deleteAuthorReferences(authorId);
        authorRepository.deleteById(authorId);
    }
}