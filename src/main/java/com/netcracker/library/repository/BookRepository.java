package com.netcracker.library.repository;

import com.netcracker.library.entity.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM library_schema.book_authors WHERE book_id = :bookId", nativeQuery = true)
    void deleteBookReferences(@Param("bookId") Long bookId);
}
