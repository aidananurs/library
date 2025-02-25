package com.netcracker.library.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categories", schema = "library_schema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "category_name", unique = true, nullable = false)
    private String categoryName;
}

