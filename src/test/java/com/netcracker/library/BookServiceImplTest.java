package com.netcracker.library;

import com.netcracker.library.entity.Author;
import com.netcracker.library.entity.Book;
import com.netcracker.library.entity.Category;
import com.netcracker.library.exceptions.NotFoundException;
import com.netcracker.library.mapper.BookMapper;
import com.netcracker.library.model.SaveBookRequest;
import com.netcracker.library.repository.AuthorRepository;
import com.netcracker.library.repository.BookRepository;
import com.netcracker.library.repository.CategoryRepository;
import com.netcracker.library.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookServiceImpl bookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave_Success() {
        SaveBookRequest request = new SaveBookRequest("SN001", "Test Title", 1L, "Test Publisher", 1992, "Shelf 4", 5, List.of(1L, 2L));
        Book book = new Book();
        book.setBookId(1L);

        when(categoryRepository.findById(request.categoryId())).thenReturn(Optional.of(new Category()));
        when(authorRepository.findAllById(request.authorIds())).thenReturn(List.of(new Author(), new Author()));
        when(bookMapper.toEntity(request)).thenReturn(book);
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        // When
        bookService.save(request);

        // Then
        verify(categoryRepository, times(1)).findById(request.categoryId());
        verify(authorRepository, times(1)).findAllById(request.authorIds());
        verify(bookMapper, times(1)).toEntity(request);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    public void testSave_CategoryNotFound() {
        SaveBookRequest request = new SaveBookRequest("SN001", "Test Title", 1L, "Test Publisher", 1992, "Shelf 4", 5, List.of(1L, 2L));

        when(categoryRepository.findById(request.categoryId())).thenReturn(Optional.empty());

        // When / Then
        assertThrows(NotFoundException.class, () -> bookService.save(request));
        verify(categoryRepository, times(1)).findById(request.categoryId());
        verify(authorRepository, never()).findAllById(anySet());
        verify(bookMapper, never()).toEntity((SaveBookRequest) any());
        verify(bookRepository, never()).save(any());
    }
}