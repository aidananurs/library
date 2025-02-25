package com.netcracker.library.model;

import java.time.LocalDate;

public record SaveAuthorRequest(
        String name,
        String nationality,
        LocalDate birthDate
) {}
