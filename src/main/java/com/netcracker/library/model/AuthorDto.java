package com.netcracker.library.model;

import java.time.LocalDate;
import java.util.List;

public record AuthorDto(
        Long authorId,
        String name,
        String nationality,
        LocalDate birthDate,
        List<BookInfo> books
) {}
