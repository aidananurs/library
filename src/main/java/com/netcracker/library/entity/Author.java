package com.netcracker.library.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "authors", schema = "library_schema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();
}
