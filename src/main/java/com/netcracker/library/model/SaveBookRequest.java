package com.netcracker.library.model;

import java.util.List;

public record SaveBookRequest(
        String serialNumber,
        String title,
        Long categoryId,
        String publisher,
        int yearPublished,
        String location,
        int copiesAvailable,
        List<Long> authorIds
) {}