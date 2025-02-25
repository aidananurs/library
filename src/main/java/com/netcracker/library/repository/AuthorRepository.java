package com.netcracker.library.repository;

import com.netcracker.library.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import jakarta.transaction.Transactional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM library_schema.book_authors WHERE author_id = :authorId", nativeQuery = true)
    void deleteAuthorReferences(@Param("authorId") Long authorId);
}
