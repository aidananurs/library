package com.netcracker.library.service;

import com.netcracker.library.entity.Author;
import com.netcracker.library.entity.Book;
import com.netcracker.library.entity.Category;
import com.netcracker.library.exceptions.NotFoundException;
import com.netcracker.library.mapper.BookMapper;
import com.netcracker.library.mapper.BookMapperImpl;
import com.netcracker.library.model.SaveBookRequest;
import com.netcracker.library.repository.AuthorRepository;
import com.netcracker.library.repository.BookRepository;
import com.netcracker.library.repository.CategoryRepository;
import com.netcracker.library.service.impl.BookServiceImpl;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
public class BookServiceTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private BookServiceImpl bookService;

    private BookMapper bookMapper = new BookMapperImpl();

    @BeforeEach
    void setUp() {
        bookService = new BookServiceImpl(bookRepository, authorRepository, categoryRepository, bookMapper);
    }

    @Test
    void testSaveBookSuccess() {
        var category = new Category();
        category.setCategoryName("Science Fiction");
        category = categoryRepository.save(category);

        var author = new Author();
        author.setName("Isaac Asimov");
        author.setNationality("American");
        author.setBirthDate(LocalDate.of(1920, 1, 2));
        author.setBooks(new ArrayList<>());
        author = authorRepository.save(author);

        var request = new SaveBookRequest(
                "SN1234", "Foundation",  category.getCategoryId(), "PublisherX", 1951, "Shelf A1", 5, List.of(author.getAuthorId())
        );

        bookService.save(request);

        List<Book> books = bookRepository.findAll();
        assertEquals(1, books.size());
        assertEquals("Foundation", books.getFirst().getTitle());
        assertEquals("Science Fiction", books.getFirst().getCategory().getCategoryName());
        assertEquals(1, books.getFirst().getAuthors().size());
    }

    @Test
    void testSaveBookThrowsNotFoundExceptionForInvalidCategory() {
        var category = new Category();
        category.setCategoryName("Science Fiction");
        category = categoryRepository.save(category);

        var author = new Author();
        author.setName("Isaac Asimov");
        author.setNationality("American");
        author.setBirthDate(LocalDate.of(1920, 1, 2));
        author.setBooks(new ArrayList<>());
        author = authorRepository.save(author);

        var request = new SaveBookRequest(
                "SN1234", "Foundation", category.getCategoryId(), "PublisherX", 1951, "Shelf A1", 5, List.of(author.getAuthorId())
        );

        assertThrows(NotFoundException.class, () -> bookService.save(request));
    }

}
