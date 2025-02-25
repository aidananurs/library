package com.netcracker.library.model;

public record BookInfo(
        Long bookId,
        String serialNumber,
        String title,
        Long categoryId,
        String publisher,
        int yearPublished,
        String location,
        int copiesAvailable
) {}