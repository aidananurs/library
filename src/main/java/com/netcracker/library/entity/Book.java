package com.netcracker.library.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Entity
@Table(name = "books", schema = "library_schema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "serial_number", unique = true, nullable = false)
    private String serialNumber;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "publisher", nullable = false)
    private String publisher;

    @Column(name = "year_published", nullable = false)
    private int yearPublished;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "copies_available", nullable = false)
    private int copiesAvailable;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
        name = "book_authors",
        schema = "library_schema",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "author_id"))
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Author> authors;
}
