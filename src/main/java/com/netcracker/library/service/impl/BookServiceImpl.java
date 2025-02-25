package com.netcracker.library.service.impl;

import com.netcracker.library.entity.Author;
import com.netcracker.library.exceptions.NotFoundException;
import com.netcracker.library.mapper.BookMapper;
import com.netcracker.library.model.BookDto;
import com.netcracker.library.model.SaveBookRequest;
import com.netcracker.library.repository.AuthorRepository;
import com.netcracker.library.repository.BookRepository;
import com.netcracker.library.repository.CategoryRepository;
import com.netcracker.library.service.BookService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.netcracker.library.utils.EntityUpdateUtil.updateIfNotNull;

@Service
@RequiredArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final BookMapper bookMapper;

    @Override
    @Transactional
    public void save(SaveBookRequest request) {
        var category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new NotFoundException("Category not found with id: " + request.categoryId()));

        var authors = authorRepository.findAllById(request.authorIds());

        if (authors.isEmpty()) {
            throw new NotFoundException("No authors found for given ids");
        }

        var book = bookMapper.toEntity(request);
        book.setCategory(category);
        book.setAuthors(new HashSet<>(authors));
        bookRepository.save(book);
    }

    @Override
    @Transactional
    public void update(Long id, SaveBookRequest request) {
        var book = bookRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Book not found with id: " + id));

        updateIfNotNull(book::setSerialNumber, request.serialNumber());
        updateIfNotNull(book::setTitle, request.title());
        updateIfNotNull(book::setPublisher, request.publisher());
        updateIfNotNull(book::setYearPublished, request.yearPublished());
        updateIfNotNull(book::setLocation, request.location());
        updateIfNotNull(book::setCopiesAvailable, request.copiesAvailable());

        if (request.categoryId() != null) {
            var category = categoryRepository.findById(request.categoryId())
                    .orElseThrow(() -> new NotFoundException("Category not found with id: " + request.categoryId()));
            book.setCategory(category);
        }

        if (request.authorIds() != null && !request.authorIds().isEmpty()) {
            var authors = authorRepository.findAllById(request.authorIds());
            if (authors.isEmpty()) {
                throw new NotFoundException("No authors found for given ids");
            }
            book.setAuthors(new HashSet<>(authors));
        }

         bookRepository.save(book);
    }

    @Override
    public Optional<BookDto> getById(Long bookId) {
        return bookRepository.findById(bookId).map(bookMapper::toDto);
    }

    @Override
    public List<BookDto> getAll() {
        return bookRepository.findAll().stream().map(bookMapper::toDto).toList();
    }

    @Override
    @Transactional
    public void deleteById(Long bookId) {
        bookRepository.deleteBookReferences(bookId);
        bookRepository.deleteById(bookId);
    }

}
