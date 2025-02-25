package com.netcracker.library.model;


import java.util.List;

public record BookDto(
        Long bookId,
        String serialNumber,
        String title,
        Long categoryId,
        String publisher,
        int yearPublished,
        String location,
        int copiesAvailable,
        List<AuthorInfo> authors
) {}